import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import $ from 'jquery';

let socket;
let connected = false;

$(document).ready(function() {
  connect();
    $("#button").on("click", (e) => {
      const text = $("#text").val();
      if (socket) {
        $("#summarization").html("");
        $("#loader").removeClass("d-none");
        socket.send(text);
      }
    });
});

function connect() {
  socket = new WebSocket("ws://" + location.host + "/summarizer");
  socket.onopen = function() {
      connected = true;
      console.log("Connected to the web socket");
  };
  socket.onmessage =function(m) {
    $("#loader").addClass("d-none");
    $("#summarization").append(m.data);
  };
}

