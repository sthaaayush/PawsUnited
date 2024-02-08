'use strict';



/**
 * add event on element
 */

const addEventOnElem = function (elem, type, callback) {
  if (elem.length > 1) {
    for (let i = 0; i < elem.length; i++) {
      elem[i].addEventListener(type, callback);
    }
  } else {
    elem.addEventListener(type, callback);
  }
}



/**
 * navbar toggle
 */

const navToggler = document.querySelector("[data-nav-toggler]");
const navbar = document.querySelector("[data-navbar]");
const navbarLinks = document.querySelectorAll("[data-nav-link]");

const toggleNavbar = function () {
  navbar.classList.toggle("active");
  navToggler.classList.toggle("active");
}

addEventOnElem(navToggler, "click", toggleNavbar);


const closeNavbar = function () {
  navbar.classList.remove("active");
  navToggler.classList.remove("active");
}

addEventOnElem(navbarLinks, "click", closeNavbar);



/**
 * active header when window scroll down to 100px
 */

const header = document.querySelector("[data-header]");
const backTopBtn = document.querySelector("[data-back-top-btn]");

const activeElemOnScroll = function () {
  if (window.scrollY > 100) {
    header.classList.add("active");
    backTopBtn.classList.add("active");
  } else {
    header.classList.remove("active");
    backTopBtn.classList.remove("active");
  }
}

addEventOnElem(window, "scroll", activeElemOnScroll);



//for form

document
.getElementById("dog_amount")
.addEventListener("change", function () {
    var numDogs = parseInt(this.value);
    var container = document.getElementById("dog_info_container");
    container.innerHTML = ""; // Clear previous content

    for (var i = 0; i < numDogs; i++) {
        var fieldset = document.createElement("fieldset");
        var legend = document.createElement("legend");
        legend.textContent = "Dog " + (i + 1) + " Information";
        fieldset.appendChild(legend);

        var labels = ["Name", "Breed", "Age", "Weight"];
        var ids = ["name", "breed", "age", "weight"];

        for (var j = 0; j < labels.length; j++) {
            var label = document.createElement("label");
            label.setAttribute("for", "dog_" + ids[j] + "_" + i);
            label.textContent = "Dog " + labels[j] + ":";
            fieldset.appendChild(label);

            var input = document.createElement("input");
            input.setAttribute("type", j == 2 ? "number" : "text");
            input.setAttribute("id", "dog_" + ids[j] + "_" + i);
            input.setAttribute("name", "dog_" + ids[j] +"[]");
            input.setAttribute("required", "");
            if (j == 3) input.setAttribute("step", "0.01");
            fieldset.appendChild(input);
        }

        container.appendChild(fieldset);
    }
});


//for qr
function generateQR() {
  var qrText = document.getElementById("qrText").innerText;
  var qrCodeDiv = document.getElementById("qrcode");

  // Clear previous QR code
  qrCodeDiv.innerHTML = "";

  // Create QR code instance
  var qrCode = qrcode(0, 'M'); // Error correction level: M (medium)
  qrCode.addData(qrText);
  qrCode.make();

  // Render QR code
  qrCodeDiv.innerHTML = qrCode.createSvgTag();
}