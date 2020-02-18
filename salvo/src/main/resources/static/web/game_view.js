const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
// get the required parameter
const param = urlParams.get('id'); // returns the value of parameter 'lan'
console.log(param)

let  lists = [];

const url = "http://localhost:8080/api/gamePlayers/" + param;
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
    dessinerTableau();
  });
let list = document.getElementById("output");

console.log(list);
function listOfGames(){
for (let i=0; i<lists.length; i++) {
  let li = document.createElement("li");
  li.innerHTML = lists[i].created + "   "+lists[i].players.firstName+ "  "+ lists[i].ships[0].shipName;
  console.log(li);
  list.appendChild(li);
}}

var entete = document.getElementById("entete");
var corps = document.getElementById("corps");
var tableauEntete= ["#",1,2,3,4,5,6,7,9,10];
var tableauCorps = ["A","B","C","D","E","F","G","H","I","J"];
//console.log(tableau_colonnes);

function dessinerTableau(){
for (let i of tableauEntete) {
//console.log("ha");
//console.log(tableau_lignes);
  var th= document.createElement("th");
  th.setAttribute("scope", "col");
  th.innerHTML = i;
  entete.appendChild(th);
}
for (let i of tableauCorps){
//console.log("ha");
//console.log(tableau_lignes);
  var tr= document.createElement("tr");
  var th= document.createElement("th");
  th.setAttribute("scope", "row");
  th.innerHTML = i;
  tr.appendChild(th);
  for (let i=0; i< tableauCorps.length; i++){
  var td = document.createElement("td");
  td.setAttribute("id", i+1);
  tr.appendChild(td);
  } corps.appendChild(tr);
}
}