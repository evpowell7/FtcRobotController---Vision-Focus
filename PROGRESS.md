# FTC Vision Progress Tracker
| Day  | Date | Core | Stretch | Notes                   |
|------|------|------|---------|-------------------------|
| I    | 10/1 | X    |         | need to find camera FOV |
| II   |      |      |         |                         |
| III  |      |      |         |                         |
| IV   |      |      |         |                         |
| V    |      |      |         |                         |
| VI   |      |      |         |                         |
| VII  |      |      |         |                         |
| VIII |      |      |         |                         |
| IX   |      |      |         |                         |
| X    |      |      |         |                         |
# Measured Values
- Camera, resolution: Logitech C720 "Webcam 1", 640x480
- Camera height (in), pitch (deg), offset from robot center (X/Y) in: h: 12.5in, angle32*, 9inY, .625inX, 
- Horizontal FOV (deg), fx (pixels): HFOV-48.8*; fx-705px
- Tag detection range with 4/2/1 3.6in tags (in):
- Motor Determinations
| Name  | ID  | Type               |
|-------|-----|--------------------|
| FR    | 0   | Rev 20:1 Hex       |
| BR    | 1   |                    |
| Claw  | 2   | Rev Core Hex       |
| Elev. | 3   | Rev UltraPlan  Hex |
| FL    | EH0 | Rev 20:1 Hex       |
| BL    | EH1 | Rev 20:1 Hex       |
REVERSED MOTORS (flat front) -> FR = BL, FL=BR;
| Name  | ID  | Type               |
|-------|-----|--------------------|
| BL    | 0   | Rev 20:1 Hex       |
| FL    | 1   |                    |
| Claw  | 2   | Rev Core Hex       |
| Elev. | 3   | Rev UltraPlan  Hex |
| BR    | EH0 | Rev 20:1 Hex       |
| FR    | EH1 | Rev 20:1 Hex       |

## Problems and fixes
- (date) problem --> fix 
# Quick GIT reference

| Scenario                                   | Command                                          |
|--------------------------------------------|--------------------------------------------------|
| File broken, last version needed           | git restore path/to/file.java                    |
| Weekly progress check                      | git log --oneline --graph --all                  |
| When did a tuned value change              | git log -p path/to/constants.java                |
| Undo a commit already pushed               | git revert <commit-id>                           |
| Put unfinished work aside to switch branch | git stash; later git stash pop                   |
| see code as it was at the end of day x     | git switch --detach dayX-done; git switch master |
| which days are done                        | git tag                                          |
