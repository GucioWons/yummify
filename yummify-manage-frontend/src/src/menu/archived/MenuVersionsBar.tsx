import {Dtos} from "../../common/dtos.ts";
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import {formatDate} from "../../common/date/dateFormatter.ts";
import Button from "../../common/button/Button.tsx";

export interface MenuVersionsBarProps {
    archivedVersions: MenuVersionArchivedListDto[]
    selectedArchivedVersion: MenuVersionArchivedListDto | null
    setSelectedArchivedVersion: (archivedVersion: MenuVersionArchivedListDto | null) => void;
}

function MenuVersionsBar(props: MenuVersionsBarProps) {
    const {archivedVersions, selectedArchivedVersion, setSelectedArchivedVersion} = props;

    return (
        <div className="menu-versions-bar">
            <div className="menu-versions-bar-item" onClick={() => setSelectedArchivedVersion(null)}>
                <Button text="Published" outlined={!selectedArchivedVersion}/>
            </div>
            {archivedVersions
                .sort((a, b) => a.version - b.version)
                .reverse()
                .map((version) =>
                    <div className="menu-versions-bar-item" onClick={() => setSelectedArchivedVersion(version)}>
                        <Button text={`v${version.version} ${formatDate(version.archivedAt)}`} outlined={selectedArchivedVersion === version}/>
                    </div>
                )
            }
        </div>
    )
}

export default MenuVersionsBar;