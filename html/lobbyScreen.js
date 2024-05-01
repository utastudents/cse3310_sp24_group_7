var idx = 0;
var lobbyId = 0;
var gameid = 0;

class UserEvent {
    PlayerIdx = 0;
    LobbyId = 0;
    Msg = null;
}

var connection = null;
var playersJoined = null;
var serverUrl;

serverUrl = "ws://" + window.location.hostname + ":9107";
// Create the connection with the server
connection = new WebSocket(serverUrl);

connection.onopen = function(evt) {
    console.log("open");
}
connection.onclose = function(evt) {
    console.log("close");
    document.getElementById("topMessage").innerHTML = "Server Offline"
}

connection.onmessage = function(evt) {
    var msg;
    msg = evt.data;

    console.log("Message received: " + msg);
    const obj = JSON.parse(msg);

    playersJoined = obj.playersJoined;

    if ('YouAre' in obj) {
        if (obj.YouAre == "PLAYER1") {
            idx = 0;
        } else if (obj.YouAre == "PLAYER2") {
            idx = 1;
        } else if (obj.YouAre == "PLAYER3") {
            idx = 2;
        } else if (obj.YouAre == "PLAYER4") {
            idx = 3;
        }
        gameid = obj.GameId;
    } else if (gameid == obj.GameId) {
        if (obj.playersJoined == 4) {}
    }
}

// Function to handle nickname submission
function handleSubmission() {
    const nickname = document.getElementById('nickname').value.trim(); // Get the entered nickname
    if (nickname === "") {
        console.log("Nickname is empty.");
        return;
    }

    console.log("Submitting nickname to server: " + nickname);

    U = new UserEvent;
    U.PlayerIdx = idx;
    U.LobbyId = lobbyId;
    U.Msg = nickname;

    connection.send(JSON.stringify(U)); // Send user event to server

    addPlayerToLobby(nickname); // Add player to the lobby
}

// Function to add the player to the lobby
function addPlayerToLobby(nickname) {
    const table = document.getElementById("gameTable2");
    const row = table.insertRow(-1);
    const playerNameCell = row.insertCell(0);
    playerNameCell.textContent = nickname;
    const joinCell = row.insertCell(1);
    const joinButton = document.createElement("button");
    joinButton.textContent = "Join";
    joinButton.style.backgroundColor = "red"; // Set button style
    joinButton.addEventListener("click", function() {
        // Handle joining the game here
    });
    joinCell.appendChild(joinButton);
}

// Event listener to the submit button in the nickname form
document.getElementById("submit").addEventListener("click", handleSubmission);
