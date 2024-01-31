import DestinationCard from '../Components/DestinationCard';
import Navbar from '../Components/Navbar';
import { useEffect, useState } from 'react';
import Axios from 'axios';
export default function HomePage() {
    const [destinations, setDestinations] = useState([]);

    useEffect(() => {
        Axios.get('http://localhost:8080/destinations')
            .then(response => {

                setDestinations(response.data);
                console.log(destinations);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);
    return (
        <div>
            <Navbar />
            <DestinationCard />
        </div>
    )
}