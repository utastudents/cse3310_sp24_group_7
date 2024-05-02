let socket; // WebSocket socket variable
const usernameInput = document.getElementById('nickname');
const submitButton = document.getElementById("submit");
let activeUsers = [];
let playerList = [];
let playerId;
let lobbyId;
let playersInLobby = 0;

idx = -1;
gameid = -1;
class UserEvent {
    Button = -1;
    GridRow = -1;
    GridColumn = -1;
    PlayerIdx = 0;
    GameId = 0;
    Msg = null;
}

let start = [-1, -1];
let end = [-1, -1];

// Function to handle nickname submission
function handleSubmission() { 
    const nickname = usernameInput.value.trim();

    if (nickname === "") {
      console.log("Nickname is empty.");
      return;
  }
  
  console.log("Submitting nickname to server: " + nickname);
  
  socket = new WebSocket("ws://" + window.location.hostname + ":9107");
  
    // Handle WebSocket socket opened
  socket.onopen = function(event) {
    console.log("WebSocket socket opened successfully.");
    const message = {
        type: "nickname",
        value: nickname
    };
    socket.send(JSON.stringify(message)); // Send nickname to server
};

    // Handle WebSocket message received
socket.onmessage = function(event) {
  console.log("Message received from server:", event.data);
  const response = JSON.parse(event.data);

  var msg;
    msg = event.data;

        console.log("Message received: " + msg);
        const obj = JSON.parse(msg);

        getVersion(obj.version);


  if (response.type === "acknowledge") {
    document.getElementById("nickname-form").style.display = "none";
    document.getElementById("lobby").style.display = "block";
    if ('YouAre' in response) {
        if (response.YouAre == "PLAYER1") 
        {
          idx = 0;
      }
      else if (response.YouAre == "PLAYER2") 
      {
          idx = 1;
      }
      else if (response.YouAre == "PLAYER3") 
      {
          idx = 2;
      }
      else if (response.YouAre == "PLAYER4") 
      {
          idx = 3;
      }
      gameid = response.GameId;
  }
    addPlayerToLobby(nickname, response.playerId); // Add the player to the lobby with player ID
    activeUsers = response.activeUsers; // Update the activeUsers array with the list from the server
    updateLobbyWithActiveUsers(activeUsers); // Update the lobby with the active users list
    console.log("Users Online: " + activeUsers);
    if(activeUsers.length >= 4)
    {
        startGame();
    }
} else if (response.type === "nickname_error") {
    console.error("Nickname error:", response.message);
    alert("Error: " + response.message);
        document.getElementById("nickname").value = ""; // Clear the input field
    } else if (response.type === "player_list_update") {
        activeUsers = response.players; // Update the activeUsers array with the list from the server
        updateLobbyWithActiveUsers(activeUsers); // Update the lobby with the active users list
    } else {

        playersJoined = obj.playersJoined;

        if(gameid == obj.GameId)
        {
            if(obj.playersJoined == 4)
            {
                document.getElementById('lobby').style.display = "none";
                document.getElementById('gameScreen').style.display = "block";
                generateGrid(obj.wordBank.grid);
                generateScoreboard(obj);
                displayWords(obj.wordBank.wordsPlaced);
            }
            else
            {
              updateGrid(obj.pickedLetters);
              updateScoreboard(obj);

              if (obj.chatBox.messageSent == true) {
                var messages = obj.chatBox.messages;
            var lastMessage = messages[messages.length - 1]; // Get the last message from the messages array
            var messagesDiv = document.getElementById('messages');
            var messageElement = document.createElement('div');
            messageElement.textContent = lastMessage; // Display the last message
            messagesDiv.appendChild(messageElement);
            messagesDiv.scrollTop = messagesDiv.scrollHeight; // Auto scroll to the bottom
          }//ERROR typing in chat before two players in game messes it up
      } 
  } 
}
};


socket.onclose = function(event) {
  console.log("WebSocket socket closed.");
  alert("WebSocket socket closed. Please try again later.");
};

socket.onerror = function(error) {
  console.error("WebSocket encountered error:", error);
  alert("WebSocket encountered error: " + error.message);
};
}

  // Modify the updateLobbyWithActiveUsers function to call startGame when there are 4 players
function updateLobbyWithActiveUsers(activeUsers) {
    const lobbyTable = document.getElementById("gameTable2");
    lobbyTable.innerHTML = ""; // Clear the existing table content

    // Loop through the activeUsers and add them to the lobby
    activeUsers.forEach(nickname => {
        addPlayerToLobby(nickname); // Call addPlayerToLobby for each player
    });
}


function addPlayerToLobby(nickname, playerId = null) {
    if (activeUsers.length > 4) {
      console.log("Cannot add player to lobby. Lobby is full.");
      return; // Exit the function if lobby is full
  }
  
  const lobbyTable = document.getElementById("gameTable2");
  
    // Create a new row for the player
  const newRow = lobbyTable.insertRow();
  
    // Create cells for player nickname and ready button
  const nicknameCell = newRow.insertCell();
  const readyButtonCell = newRow.insertCell();
  
    // Set the player's nickname in the nickname cell and add the "nickname" class
  nicknameCell.textContent = nickname;
  nicknameCell.classList.add("nickname");
  
    // Create a ready button
  const readyButton = document.createElement("button");
  readyButton.textContent = "Ready";
  readyButton.classList.add("ready-button");
    readyButton.dataset.playerId = playerId; // Set the player ID as a data attribute

    // Add an event listener to the ready button
    readyButton.addEventListener("click", function() {
      // Get the player ID associated with the clicked button
      const clickedPlayerId = readyButton.dataset.playerId;
      
      // Check if the clicked button belongs to the current player
      if (clickedPlayerId === playerId) {
        // Toggle button color
        if (readyButton.classList.contains("ready")) {
          readyButton.classList.remove("ready");
      } else {
          readyButton.classList.add("ready");
      }

        // Update the lobby after toggling the button
      updateLobbyWithActiveUsers(activeUsers);
  }
});

    // Append the ready button to the ready button cell
    readyButtonCell.appendChild(readyButton);

    // Increment the count of players in the lobby
    playersInLobby++;
}

function joinGame() {
    const nickname = document.getElementById('nickname').value.trim(); // Get the entered nickname
    const playerId = ""; // Get the player ID from somewhere (maybe from the server response)
    const queryString = `?nickname=${encodeURIComponent(nickname)}&playerId=${encodeURIComponent(playerId)}`; // Construct the query string
    // window.location.href = `gameScreen.html${queryString}`; // Redirect to the game screen with the query string
}



  // Event listener to the submit button in the nickname form
document.getElementById("submit").addEventListener("click", handleSubmission);

function startGame() {

    console.log("Starting the game...");
    // Send a "new_game" message to the server

    // Create a new_game message to send to the server
    const message = {
        type: "new_game"
    };

    // Send the start_game message to the server
    socket.send(JSON.stringify(message));
}

document.getElementById("sendButton").addEventListener("click", chat);
document.getElementById("sendButton").addEventListener("click", chat);
document.getElementById("message").addEventListener("keypress", function(event) {
    if (event.keyCode === 13) {  // 13 is the keycode for the Enter key
        chat();
        event.preventDefault(); // Prevent the default action (form submission, etc.)
    }
});

function chat() {
    U = new UserEvent();
    U.Button = 9;
    if (idx == 0)
        U.PlayerIdx = "PLAYER1";
    else if (idx == 1)
        U.PlayerIdx = "PLAYER2";
    else if (idx == 2)
        U.PlayerIdx = "PLAYER3";
    else if (idx == 3)
        U.PlayerIdx = "PLAYER4";
    U.GameId = gameid;

    var inputElement = document.getElementById('message');
    var inputValue = inputElement.value;
    U.Msg = inputValue;
    document.getElementById('message').value = null;

    socket.send(JSON.stringify(U));
    console.log(JSON.stringify(U));
}  // Existing game UI update logic here

function generateGrid(grid) {
    var table = document.createElement('table');
    grid.forEach(function (row, rowIndex) {
        var tr = document.createElement('tr');
        row.forEach(function (column, columnIndex) {
            var td = document.createElement('td');
            var button = document.createElement('button');
            button.textContent = column;
                // Set up an onclick function that calls buttonclick with parameters
            button.onclick = function () {
                    buttonclick(rowIndex, columnIndex); // Assuming you want to pass row and column indexes
                };
                td.appendChild(button);
                tr.appendChild(td);
            });
        table.appendChild(tr);
    });
    var container = document.getElementById('grid');
        container.innerHTML = ''; // Clear previous table
        container.appendChild(table);
    }

    function updateGrid(letters) {
      if(start[0] != -1 && end[0] != -1)
      {
        var buttonElementStart = document.querySelector('#grid tr:nth-child(' + (start[0] + 1) + ') td:nth-child(' + (start[1] + 1) + ') button');
        var buttonElementEnd = document.querySelector('#grid tr:nth-child(' + (end[0] + 1) + ') td:nth-child(' + (end[1] + 1) + ') button');
        buttonElementStart.style.backgroundColor = '';
        buttonElementEnd.style.backgroundColor = '';
        start = [-1, -1];
        end = [-1, -1];
    }
    letters.forEach(function (letter) {
        var buttonElement = document.querySelector('#grid tr:nth-child(' + (letter.row + 1) + ') td:nth-child(' + (letter.col + 1) + ') button');
        if (letter.playerId == 'PLAYER1') {
          buttonElement.style.backgroundColor = 'yellow';
      } else if (letter.playerId == 'PLAYER2') {
          buttonElement.style.backgroundColor = 'green';
      } else if (letter.playerId == 'PLAYER3') {
          buttonElement.style.backgroundColor = 'red';
      } else if (letter.playerId == 'PLAYER4') {
          buttonElement.style.backgroundColor = 'blue';
      }
  });
}

function generateScoreboard(obj) {
    var scores = obj.scoreBoard.scores;
    var scoreboard = document.getElementById('scoreBoard');
    scoreboard.innerHTML = ''; // Clear previous scores
    scores.forEach(function (score, index) {
        var p = document.createElement('p');
        p.textContent = obj.playerNames[index] + ": 0";
        scoreboard.appendChild(p);
    });
}

function updateScoreboard(obj) {
    var scores = obj.scoreBoard.scores;
    var scoreboard = document.getElementById('scoreBoard');
    scoreboard.innerHTML = ''; // Clear previous scores
    scores.forEach(function (score, index) {
        var p = document.createElement('p');
        p.textContent = obj.playerNames[index] + ": " + score;
        scoreboard.appendChild(p);
    });
}

    function displayWords(words) {
        var listContainer = document.getElementById('wordList');
        listContainer.innerHTML = ''; // Clear existing words
        var ul = document.createElement('ul');
        words.forEach(function (word) {
            var li = document.createElement('li');
            li.textContent = word;
            ul.appendChild(li);
        });
        listContainer.appendChild(ul);
    }


    function buttonclick(i, j) {
      U = new UserEvent();
      U.GridRow = i;
      U.GridColumn = j;
      if (idx == 0)
        U.PlayerIdx = "PLAYER1";
    else if (idx == 1)
        U.PlayerIdx = "PLAYER2";
    else if (idx == 2)
        U.PlayerIdx = "PLAYER3";
    else if (idx == 3)
        U.PlayerIdx = "PLAYER4";

    U.GameId = gameid;

    if(start[0] == -1)
    {
        var buttonElement = document.querySelector('#grid tr:nth-child(' + (i + 1) + ') td:nth-child(' + (j + 1) + ') button');

        if(U.PlayerIdx == "PLAYER1"){
          buttonElement.style.backgroundColor = 'yellow';
      }
      else if(U.PlayerIdx == "PLAYER2"){
          buttonElement.style.backgroundColor = 'green';
      }
    //Created by Abubakar Kassim
      else if (U.PlayerIdx == "PLAYER3")
      {
          buttonElement.style.backgroundColor = 'red';
      }
      else if (U.PlayerIdx == "PLAYER4")
      {
          buttonElement.style.backgroundColor = 'blue';
      }
      start[0] = i;
      start[1] = j;
  }
  else if(end[0] == -1)
  {
    var buttonElement = document.querySelector('#grid tr:nth-child(' + (i + 1) + ') td:nth-child(' + (j + 1) + ') button');

    if(U.PlayerIdx == 'PLAYER1'){
      buttonElement.style.backgroundColor = 'yellow';
  }
  else if(U.PlayerIdx == 'PLAYER2')
  {
      buttonElement.style.backgroundColor = 'green';
  }
    //Created By Abubakar Kassim
  else if (U.PlayerIdx == 'PLAYER3')
  {
      buttonElement.style.backgroundColor = 'red';
  }
  else if (U.PlayerIdx == 'PLAYER4')
  {
      buttonElement.style.backgroundColor = 'blue';
  }

  end[0] = i;
  end[1] = j;
}
socket.send(JSON.stringify(U));
console.log(JSON.stringify(U));
}

function getVersion(version)
{
    head = document.getElementById("title");
    head.innerHTML = version;
}


