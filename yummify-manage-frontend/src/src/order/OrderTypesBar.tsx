export interface OrderTypesBarProps {
    selectedTab: 'OLD' | 'CURRENT';
    setSelectedTab: (tab: 'OLD' | 'CURRENT') => void;
}

function OrderTypesBar(props: OrderTypesBarProps) {
    const {selectedTab, setSelectedTab} = props;

    return (
        <div style={{display: "flex", alignItems: "center", gap: 8, marginBottom: 12}}>
            <button
                onClick={() => setSelectedTab('CURRENT')}
                className={`menu-section-bar-item ${selectedTab === 'CURRENT' ? "active" : ""}`}
            >
                CURRENT
            </button>
            <button
                onClick={() => setSelectedTab('OLD')}
                className={`menu-section-bar-item ${selectedTab === 'OLD' ? "active" : ""}`}
            >
                OLD
            </button>
        </div>
    );
}

export default OrderTypesBar;