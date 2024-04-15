let socket; // WebSocket connection variable

// Function to handle the submit button click event
function handleSubmission() {
    let nickname = document.getElementById("nickname").value;

    if (nickname === "") {
        console.log("Nickname is empty.");
        return;
    }

    console.log("Submitting nickname to server: " + nickname);

    // Create a WebSocket connection to your server
    socket = new WebSocket("ws://localhost:9880");

    // Handle incoming messages from the server
    socket.onmessage = function(event) {
        const response = JSON.parse(event.data);
        if (response.type === "acknowledge") {
            // Display success message and show lobby screen
            console.log(response.message);
            document.getElementById("nickname-form").style.display = "none";
            document.getElementById("lobby").style.display = "block";
        } else if (response.type === "error") {
            // Display error message to user
            alert(response.message);
            // Clear nickname input field
            document.getElementById("nickname").value = "";
        } 
    };

    // Send a message containing the nickname to the server
    socket.onopen = function(event) {
        const message = {
            type: "nickname",
            value: nickname
        };
        socket.send(JSON.stringify(message));
    };
}

// Function to show the lobby screen
function showLobbyScreen() {
    document.getElementById("gameScreen").style.display = "none";
    document.getElementById("lobby").style.display = "block";
}

// Example function for starting the game (replace with your actual function)
function startGame() {
    console.log("Starting the game...");
    // Add game start logic here
    document.getElementById("lobby").style.display = "none";
    document.getElementById("gameScreen").style.display = "block";

    // Generate the game table dynamically
    const gameTable = document.getElementById("gameTable");
    gameTable.innerHTML = "";
    for (let i = 0; i < 25; i++) {
        let row = document.createElement("tr");
        for (let j = 0; j < 25; j++) {
            let cell = document.createElement("td");
            let index = i * 25 + j;
            let button = document.createElement("input");
            button.type = "button";
            button.value = "?";
            button.id = "b" + index;
            button.onclick = function() {
                buttonclick(index);
            };
            cell.appendChild(button);
            row.appendChild(cell);
        }
        gameTable.appendChild(row);
    }
}

// Function to handle button click on the game screen
function buttonclick(index) {
    console.log("Button clicked: " + index);
    
}

// Function to handle the submit button on the game screen
function submit() {
    
}

// Add an event listener to the submit button in the nickname form
document.getElementById("submit").addEventListener("click", handleSubmission);
