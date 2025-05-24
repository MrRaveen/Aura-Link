import React from "react";
import '../CSS/navBar.css';

function StickyNavbar() {
    return (
        <nav className="navbar">
            <div className="nav-container">
               
                <div className="nav-links">
                    <a href="/mainMenuTo" className="nav-item active">Home</a>
                    <a href="/notificationPage" className="nav-item">Notifications</a>
                    <a href="#contact" className="nav-item">Friends</a>
                    <a href="#contact" className="nav-item">Messages</a>
                    <a href="/" className="nav-item">Log Out</a>
                    <a href="/accountPath" className="nav-item account">
                        Account
                    </a>
                </div>
            </div>
        </nav>
    );
}

export default StickyNavbar;