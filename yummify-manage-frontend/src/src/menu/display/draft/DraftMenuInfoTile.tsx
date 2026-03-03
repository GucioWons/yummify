function DraftMenuInfoTile() {
    return (
        <div style={{backgroundColor: "#FFFBEB", borderRadius: 8, borderColor: "#FDE68A", borderWidth: 2, borderStyle: "solid", padding: "10px", marginBottom: 16}} >
            <div style={{color: "#78350F", fontWeight: "bold"}}>
                Editing Draft Menu
            </div>
            <div style={{color: "#B45309"}}>
                Changes will not be visible to customers until you publish this menu
            </div>
        </div>
    )
}

export default DraftMenuInfoTile;