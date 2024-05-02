import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import $ from 'jquery';

$(document).ready(function() {
    $("#button").on("click", (e) => {
      const text = $("#text").val();
      $("#loader").removeClass("d-none");
      $.post('/rn', {text: text}, (data) => {
        $("#summarization").html(data);
        $("#loader").addClass("d-none");
      });
    });
});

