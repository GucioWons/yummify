import {useContext, useState} from "react";
import PageTitle from "../common/PageTitle.tsx";
import CurrentOrderList from "./current/CurrentOrderList.tsx";
import OldOrderList from "./old/OldOrderList.tsx";
import OrderTypesBar from "./OrderTypesBar.tsx";
import './OrderDashboard.css';
import {useRestaurantOrdersWebSocket} from "./ws/useOrdersWebSocket.ts";
import {RestaurantContext} from "../restaurant/context/RestaurantContext.tsx";
import {AuthContext} from "../auth/context/AuthContext.tsx";

function OrderDashboardPage() {
    const [selectedTab, setSelectedTab] = useState<'OLD' | 'CURRENT'>('CURRENT');

    const {restaurant} = useContext(RestaurantContext)
    const {token} = useContext(AuthContext);

    useRestaurantOrdersWebSocket(restaurant!.id, token!);

    return (
        <>
            <PageTitle
                title='Orders'
                description='Monitor and manage restaurant orders'
            />

            <OrderTypesBar selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
            {selectedTab === 'CURRENT' && <CurrentOrderList />}
            {selectedTab === 'OLD' && <OldOrderList />}
        </>
    )
}

export default OrderDashboardPage;