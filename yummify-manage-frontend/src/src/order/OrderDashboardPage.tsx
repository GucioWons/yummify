import {useState} from "react";
import PageTitle from "../common/PageTitle.tsx";
import CurrentOrderList from "./current/CurrentOrderList.tsx";
import OldOrderList from "./old/OldOrderList.tsx";
import OrderTypesBar from "./OrderTypesBar.tsx";
import './OrderDashboard.css';

function OrderDashboardPage() {
    const [selectedTab, setSelectedTab] = useState<'OLD' | 'CURRENT'>('CURRENT');

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