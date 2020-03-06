let  lists = [];
let jeux =[];
const url = "http://localhost:8080/api/leaderboard/";
const ol = "http://localhost:8080/api/games/";
console.log(url)
/*fetch(url, {
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
  });*/
  Promise.all([
  	fetch(url).then(res => res.json()),
  	fetch(ol).then(res => res.json())
  ]).then(function (data) {
  		// Log the data to the console
  		// You would do something with both sets of data here
        lists = data[0];
        jeux = data[1];
   		console.log(jeux);
   		console.log(data[1][0].created);
   		listOfGames();
   		listeDeJeux();
  	}).catch(function (error) {
  		// if there's an error, log it
  		console.log(error);
  	});


//let list = document.getElementById("output");
let table = document.getElementById("table");
next: function listOfGames(){
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
function join(){
}
let listeDejeux = document.getElementById("listeDeJeux");
function listeDeJeux(){
let game = document.getElementById("listeDeJeux");
let game1 = document.createElement("p");
let a1 = document.createElement("a");
let a2 = document.createElement("a");
//<a href="url">link text</a>
a1.setAttribute("href", "http://localhost:8080/web/game_view.html?id=1");
a1.innerHTML = jeux[0].gamePlayers[0].firstName;
let bouton = document.createElement("button");
let game2 = document.createElement("p");
game1.innerHTML = jeux[0].created + ":    "+ a1 + "  VS  " + jeux[0].gamePlayers[1].firstName;
bouton.setAttribute("onclick", "join()");
bouton.innerHTML = "join";
game2.innerHTML = jeux[2].created + ":    "+ jeux[2].gamePlayers[0].firstName;
game.appendChild(game1);
game.appendChild(a1);
game.appendChild(bouton);
game.appendChild(game2);
}