import axios from "axios";
import { useEffect, useState } from "react";

const Inventory = ({props}:{props:any}) => {

    const [inventoryList, setInventoryList] = useState([])

    const fetchInventory = async () => {
        try {
            const response = await
            axios.get('http://localhost:8080/api/inventory/' + props)
            console.log("props: " + props)
            setInventoryList(response.data);
            } catch (error) {
            console.error('Error fetching inventory data: ', error)
            }
        }
      
    useEffect(() => {
        fetchInventory();
    }, [])

    return (
        <div className="container-jawn-login-form">
            <h1 className="title-jawn">Inventory</h1>
            <div className="">
                <div className="grid-container">
                    {[...Array(12).keys()].map(index => (
                        <div key={index} className="grid-item">
                            {inventoryList[index]}
                        </div>
                    ))}
                </div>
            </div>
            {/* <div className="toss-btn">
                <button className="btn">Toss</button>
            </div> */}
        </div>
    );
  };
  
  export default Inventory;
  