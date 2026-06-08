import {Dtos} from "../../common/dtos.ts";
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import {formatDate} from "../../common/date/dateFormatter.ts";

export interface MenuVersionsBarProps {
    archivedVersions: MenuVersionArchivedListDto[]
    setSelectedArchivedVersion: (archivedVersion: MenuVersionArchivedListDto | null) => void;
}

function MenuVersionsBar(props: MenuVersionsBarProps) {
    const {archivedVersions, setSelectedArchivedVersion} = props;

    return (
        <div className="menu-versions-bar">
            <div className="menu-versions-bar-item" onClick={() => setSelectedArchivedVersion(null)}>
                <div>
                    Published
                </div>
            </div>
            {archivedVersions
                .sort((a, b) => a.version - b.version)
                .reverse()
                .map((version) =>
                    <div className="menu-versions-bar-item" onClick={() => setSelectedArchivedVersion(version)}>
                        <div>
                            v{version.version} {formatDate(version.archivedAt)}
                        </div>
                    </div>
                )
            }
        </div>
    )
}

export default MenuVersionsBar;