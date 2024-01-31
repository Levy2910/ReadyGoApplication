import React from "react";
export default function DestinationCard(props) {
    const { imageURL, destinationName, destinationDescription, destinationPrice } = props;
    return (
        <div class="card" style={{ width: '18rem' }}>
            <img class="card-img-top" src={imageURL} alt="Card image cap" />
            <div class="card-body">
                <h5 class="card-title">{destinationName}</h5>
                <p class="card-text">{destinationDescription}</p>
                <p class="card-text">{destinationPrice}</p>
                <a href="#" class="btn btn-primary">View Details</a>
            </div>
        </div>
    )
}