let  lists = [];

const url = "http://localhost:8080/api/leaderboard/";
console.log(url)
fetch(url, {
  headers: {
    "Content-Type": "application/json",
  }
})
  .then(resp => resp.json())
  .then(function(resp) {
    console.log("lo", resp);
   // console.log(resp[0].totalScore);
    lists = resp;
    listOfGames();
    //console.log(listOfGames());
  });

//let list = document.getElementById("output");
let table = document.getElementById("table");
function listOfGames(){
for (let i of lists) {
console.log(lists.length);
let tr = document.createElement("tr");
let td1 = document.createElement("td");
let td2 = document.createElement("td");
let td3 = document.createElement("td");
let td4 = document.createElement("td");
let td5 = document.createElement("td");

  td1.innerHTML = i.name;
  td2.innerHTML = i.totalScore;
  td3.innerHTML = i.totalWin;
  td4.innerHTML = i.totalLoss;
  td5.innerHTML = i.totalTies;

  tr.appendChild(td1);
  tr.appendChild(td2);
  tr.appendChild(td3);
  tr.appendChild(td4);
  tr.appendChild(td5);

  table.appendChild(tr);
  }
}