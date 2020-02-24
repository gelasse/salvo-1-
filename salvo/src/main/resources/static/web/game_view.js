const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
// get the required parameter
const param = urlParams.get('id'); // returns the value of parameter 'lan'
console.log(param)

let  lists = [];
let aa = "backgroundColor";
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
    document.getElementById("A10").style[aa] = "red";
    position();
    positionnerShips();
    positionnerShips1();
    //positionnerShips2(0);
  });
let list = document.getElementById("output");

console.log(list);
function listOfGames(){
for (let i=0; i<lists.length; i++) {
  let li = document.createElement("li");
  li.innerHTML = lists[i].created + "   "+lists[i].players.firstName+ "  "+ lists[i].ships[0].shipName;
  console.log(li);
  console.log(lists[0].ships[1].shipLocations);
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
for (let i=0; i<tableauCorps.length; i++){
//console.log("ha");
//console.log(tableau_lignes);
  var tr= document.createElement("tr");
  var th= document.createElement("th");
  th.setAttribute("scope", "row");
  th.innerHTML = tableauCorps[i];
  tr.appendChild(th);
  for (let z=1; z<tableauEntete.length; z++){
  var td = document.createElement("td");
  td.setAttribute("id", (tableauCorps[i] + "" + tableauEntete[z]));
  tr.appendChild(td);
 } corps.appendChild(tr);
}}
function positionnerShips(){
let id = lists[0].ships[0].shipLocations;
for (let i=0; i<id.length; i++){
document.getElementById(id[i]).style.backgroundColor = "orange"}

//console.log(lets[0].ships);
}
function positionnerShips1(){
let ships = lists[0].ships
for (let i=0; i<ships.length; i++){
let locations = lists[0].ships[i].shipLocations;
for (let j=0; j<locations.length; j++){
document.getElementById(locations[j]).style.backgroundColor = "orange"}
}
//console.log(lets[0].ships);
}
function positionnerShips2(2){
let id = lists[0].ships[2].shipLocations;
for (let i=0; i<id.length; i++){
document.getElementById(id[i]).style.backgroundColor = "orange"}

//console.log(lets[0].ships);
}
function position(){
positionnerShips();
positionnerShips1();
positionnerShips2();}