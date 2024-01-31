import React from 'react';
// /images/Untitled design.png 
export default function Navbar() {
    return (
        <nav class="navbar bg-light p-3 mb-5">
            <a class="navbar-brand" href="#">
                <img class="rounded-circle" src="/images/Untitled design.png" width="50" height="50" alt="" />
            </a>
            <ul class="navbar-nav list-group-horizontal gap-5">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
            </ul>
        </nav>
    );
};
