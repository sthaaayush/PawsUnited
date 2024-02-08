//for login animation
const container = document.getElementById('login_container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});


//for pop animation
function generateQR() {
    var qrText = document.getElementById("qrText").innerText;
    var qrCodeDiv = document.getElementById("qrcode");

    // Clear previous QR code
    qrCodeDiv.innerHTML = "";

    // Create QR code instance
    var qrCode = qrcode(0, "M");
    qrCode.addData(qrText);
    qrCode.make();

    // Render QR code
    qrCodeDiv.innerHTML = qrCode.createSvgTag();
}

function showModal() {
    // Get the modal
    var modal = document.getElementById("myModal");
    // Display the modal
    modal.style.display = "block";
}

function closeModal() {
    // Get the modal
    var modal = document.getElementById("myModal");
    // Hide the modal
    modal.style.display = "none";
}

// Close the modal when clicking anywhere outside of it
window.onclick = function (event) {
    var modal = document.getElementById("myModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
};