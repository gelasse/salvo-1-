function login() {
  var form = document.getElementById("login-form")
  //console.log(form);
  $.post("/api/login",
         { userName: form["username"].value,
           password: form["password"].value })
   .done(function(){console.log("logged in!")})
   .fail();
}

function logout() {
  //evt.preventDefault();
  $.post("/api/logout")
   .done(function(){console.log("logged out!")})
   .fail(function(error){console.log(error)});
}
