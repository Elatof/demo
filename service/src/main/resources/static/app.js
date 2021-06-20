var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#cars").html("");
}

function connect() {
    var socket = new SockJS('/demo-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/cars', function (message) {
            showMessage(message.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function callCars() {
    stompClient.send("/app/cars", {});
}

function callCarById() {
    let id = $("#car_id").val();
    stompClient.send("/app/cars/" + id, {});
}

function addCar() {
    stompClient.send("/app/add-car", {}, $('#car_body').val());
}

function updateCar() {
    stompClient.send("/app/update-car", {}, $('#car_body_update').val());
}

function showMessage(message) {
    $("#cars").append("<tr><td>" + message + "</td></tr>");
}

function deleteCarById() {
    let id = $("#car_id_delete").val();
    stompClient.send("/app/delete-cars/" + id, {});
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#call" ).click(function() { callCars(); });
    $( "#call_id" ).click(function() { callCarById(); });
    $( "#call_add" ).click(function() { addCar(); });
    $( "#call_update" ).click(function() { updateCar(); });
    $( "#call_delete" ).click(function() { deleteCarById(); });
});

