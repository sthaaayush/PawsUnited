
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- 
          - primary meta tag
        -->
        <title>PAWS UNITED-APPOINTMENT</title>
        <meta name="title" content="PAWS UNITED" />
        <meta name="description" content="This is an eCommerce html template made by team PAWS UNITED" />

        <!-- 
          - favicon
        -->
        <link rel="shortcut icon" href="./pawsunited_logo.png" type="image/png" />

        <!-- 
          - custom css link
        -->
        <link rel="stylesheet" href="assets/css/appointment.css" />

        <!-- 
          - google font link
        -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Bangers&family=Carter+One&family=Nunito+Sans:wght@400;700&display=swap"
            rel="stylesheet" />

        <!-- 
          - preload images
        -->
        <link rel="preload" as="image" href="./assets/images/hero-banner.jpg" />

        <script src="https://cdn.jsdelivr.net/npm/qrcode-generator/qrcode.min.js"></script>
    </head>

    <body id="top" onload="generateQR()">
        <!-- 
          - #HEADER
        -->

        <header class="header" data-header>
            <div class="container">
                <button class="nav-toggle-btn" aria-label="toggle manu" data-nav-toggler>
                    <ion-icon name="menu-outline" aria-hidden="true" class="menu-icon"></ion-icon>
                    <ion-icon name="close-outline" aria-label="true" class="close-icon"></ion-icon>
                </button>

                <a href="#" class="logo">Paws United</a>

                <nav class="navbar" data-navbar>
                    <ul class="navbar-list">
                        <li class="navbar-item">
                            <a href="index.html" class="navbar-link" data-nav-link>Home</a>
                        </li>

                        <li class="navbar-item">
                            <a href="index.html#about" class="navbar-link" data-nav-link>About</a>
                        </li>

                        <li class="navbar-item">
                            <a href="shop.html" class="navbar-link" data-nav-link>Shop</a>
                        </li>

                        <li class="navbar-item">
                            <a href="appointment.html" class="navbar-link" data-nav-link>Appointments</a>
                        </li>

                        <li class="navbar-item">
                            <a href="#footer" class="navbar-link" data-nav-link>Contact</a>
                        </li>

                        <li class="navbar-item">
                            <a href="customer_details.jsp" class="navbar-link" data-nav-link>Customers</a>
                        </li>
                    </ul>

                    <a href="#" class="navbar-action-btn">Log In</a>
                </nav>

                <div class="header-actions">
                    <button class="action-btn" aria-label="Search">
                        <ion-icon name="search-outline" aria-hidden="true"></ion-icon>
                    </button>

                    <button class="action-btn user" aria-label="User">
                        <ion-icon name="person-outline" aria-hidden="true"></ion-icon>
                    </button>
                </div>
            </div>
        </header>
        <%
            if (request.getAttribute("submitFlag") != null) {
                String flag = (String) request.getAttribute("submitFlag");
                if (flag.equalsIgnoreCase("true")) {
        %>
        <!-- Confirmation message -->
        <div class="confirm-box" id="confirm-box">
          <div class="content">
            <div id="qrText">
              <span>Owner ID: 558</span>
              <span>Dog ID: 567</span>
              <span>Dog Name: Buddy</span>
              <span>Dog Breed: Boxer</span>
              <span>Created On: 23/2</span>
            </div>
            <div id="qrcode"></div>
            <button onclick="closeModal()">X</button>
          </div>
        </div>
        <%
                }
            }
        %>
        <!-- Body -->
        <div class="container-form">
            <form action="data_pass" method="post" id="customer_dog_form">
                <fieldset>
                    <legend>Customer Information</legend>
                    <label for="customer_name">Customer Name:</label>
                    <input type="text" id="customer_name" name="customer_name" required />

                    <label for="phone_number">Phone Number:</label>
                    <input type="text" id="phone_number" name="phone_number" required />

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required />

                    <label for="dog_amount">Number of Dogs:</label>
                    <select id="dog_amount" name="dog_amount" style="color: #666">
                        <option disabled selected>No. Of Dogs</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </fieldset>

                <div id="dog_info_container"></div>

                <input type="submit" value="Add" />
            </form>
        </div>

        <!-- 
          - #FOOTER
        -->
        <footer class="footer" style="background-image: url('./assets/images/footer-bg.jpg')">
            <div class="footer-top section" id="footer">
                <div class="container">
                    <div class="footer-brand">
                        <a href="#" class="logo"><img src="./pawsunited_logo.png" alt=" Paw United Logo" height="300px" width="300px"/></a>

                        <p class="footer-text">
                            If you have any question, please contact us at
                            <a href="mailto:[pawsunited]@gmail.com" class="link">pawsunited@gmail.com</a>
                        </p>

                        <ul class="contact-list">
                            <li class="contact-item">
                            <ion-icon name="location-outline" aria-hidden="true"></ion-icon>

                            <address class="address">
                                30 Buttonwood St.Pataskala OH 43062
                            </address>
                            </li>

                            <li class="contact-item">
                            <ion-icon name="call-outline" aria-hidden="true"></ion-icon>

                            <a href="tel:9741XXXXXX" class="contact-link">9741-XXX-XXX</a>
                            </li>
                        </ul>

                        <ul class="social-list">
                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-facebook"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-twitter"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-pinterest"></ion-icon>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="social-link">
                                    <ion-icon name="logo-instagram"></ion-icon>
                                </a>
                            </li>
                        </ul>
                    </div>

                    <ul class="footer-list">
                        <li>
                            <p class="footer-list-title">Corporate</p>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Careers</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">About Us</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Contact Us</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">FAQs</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Vendors</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Affiliate Program</a>
                        </li>
                    </ul>

                    <ul class="footer-list">
                        <li>
                            <p class="footer-list-title">Information</p>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Online Store</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Privacy Policy</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Refund Policy</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Shipping Policy</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Terms of Service</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Track Order</a>
                        </li>
                    </ul>

                    <ul class="footer-list">
                        <li>
                            <p class="footer-list-title">Services</p>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Grooming</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Positive Dog Training</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Veterinary Services</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Petco Insurance</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Pet Adoption</a>
                        </li>

                        <li>
                            <a href="#" class="footer-link">Resource Center</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">
                    <p class="copyright">
                        &copy; 2024 Made with ♥ by
                        <a href="#" class="copyright-link">PawsUnited Team</a>
                    </p>

                    <img src="./assets/images/payment.png" width="397" height="32" loading="lazy" alt="payment method"
                         class="img" />
                </div>
            </div>
        </footer>

        <!-- 
          - #BACK TO TOP
        -->

        <a href="#top" class="back-top-btn" aria-label="back to top" data-back-top-btn>
            <ion-icon name="chevron-up" aria-hidden="true"></ion-icon>
        </a>

        <!-- 
          - custom js link
        -->
        <script src="./assets/js/appointment.js" defer></script>
        <!-- 
          - ionicon link
        -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>