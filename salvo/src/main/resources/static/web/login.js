let lists = [];
const url = "http://localhost:8080/api/gamePlayers/"
fetch(url, {
  headers: {
    "Content-Type": "application/json",
  }
})
  .then(resp => resp.json())
  .then(function(resp) {
    //console.log(resp);
    lists = resp;
    console.log(lists[1].id_gamePlayer)
    //login();
  });


function login() {
  var form = document.getElementById("login-form")
  //console.log(form);
  $.post("/api/login",
         { userName: form["username"].value,
           password: form["password"].value })
   .done(function(){window.location.href="http://localhost:8080/web/game.html"})
   .fail(function(){alert("faux");});
}

function logout() {
  //evt.preventDefault();
  $.post("/api/logout")
   .done(function(){window.location.href="http://localhost:8080/web/login.html"})
   .fail(function(error){console.log(error)});
}
