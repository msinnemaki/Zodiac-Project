let userStorage = localStorage.getItem("currentUser");
let currentUser = JSON.parse(userStorage);
let moodButton = document.getElementById("moodButton");

console.log(currentUser);

let greeting = document.getElementById("greeting")
greeting.innerText = `Welcome ${currentUser.first_name}, Check Todays Horoscope`;

let button = document.getElementById("button")

button.addEventListener("click", async() => {

try{


  const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${currentUser.zodiac}/today`);

  if(!raw_response.ok){
    throw new Error(raw_response.status)
  }

  const json_data = await raw_response.json();

  console.log(json_data)

  var input = document.getElementById("input");
  var horoscope = document.createElement('h2');

  horoscope.innerHTML = `Todays horoscope: ${json_data.horoscope}`;
  input.append(horoscope);

  var b = document.createElement('br');
  input.append(b);

  var mood = document.getElementById("mood");
  mood.innerText = `${json_data.meta.mood}`;




}catch(error){
  console.log(error);
}
}
);

moodButton.addEventListener("click", (event) => {
  event.preventDefault();

  let xhttp = new XMLHttpRequest();

  let userid = currentUser.user_id;
  console.log(userid)
  var mood2 = document.getElementById("mood");
  let mood3 = mood2.innerText;
  console.log(mood3)

  let moodInfo = {
    user_id:userid,
    mood:mood3,
  }
  console.log(moodInfo);

  xhttp.onreadystatechange = function(){
    if(this.readyState == 4 && xhttp.status === 200){
      console.log(xhttp.responseText);

      let data = JSON.parse(xhttp.responseText);
      console.log(data);

      
    }
    else if(this.readyState == 4 && xhttp.status == 204)
    {
      console.log(xhttp.responseText);
      alert("Failed to Login: Status Code - "+xhttp.status)
    }
  };

  xhttp.open("POST", `http://localhost:8080/ZodiacProject/moodServlet` );
  xhttp.setRequestHeader("Access-Control-Allow-Origin","*");
  xhttp.setRequestHeader("Content-Type","application/json");
  xhttp.send(JSON.stringify(moodInfo));
});









