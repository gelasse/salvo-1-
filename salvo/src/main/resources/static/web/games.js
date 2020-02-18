let  lists = [];

const url = "http://localhost:8080/api/games/";
console.log(url)
fetch(url, {
  headers: {
    "Content-Type": "application/json",
  }
})
  .then(resp => resp.json())
  .then(function(resp) {
    console.log("lo", resp);
    lists = resp;
    listOfGames();
  });

let list = document.getElementById("output");

console.log(list);
function listOfGames(){
for (let i=0; i<lists.length; i++) {
  let li = document.createElement("li");
  li.innerHTML = lists[i].created + "   " +lists[i].gamePlayers[0].userName;
  console.log(li);
  list.appendChild(li);
}}