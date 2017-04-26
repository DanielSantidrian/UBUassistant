"use strict";



$("#user-input-form").on("submit", function (e) {

	var outputArea = $("#chat-output");
	
  e.preventDefault();

  var message = $("#user-input").val();

  outputArea.append("\n    <div class='bot-message'>\n      <div class='message'>\n        " + message + "\n      </div>\n    </div>\n  ");

  setTimeout(function () {
    outputArea.append("\n      <div class='user-message'>\n        <div class='message'>\n         I can't actually talk to you.\n        </div>\n      </div>\n    ");
  }, 250);

  $("#user-input").val("");
});