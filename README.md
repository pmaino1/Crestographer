# Crestographer
> A tool for Fire Emblem games.

View and compare the stats of your Fire Emblem units! Currently only supports a small roster of the cast from Three Houses, but eventually it will include characters from the full game, and other Fire Emblem games!

Choose the stat you wish to highlight, then hit search to display the characters! The data is updated live using the Google Sheets API, and may need some time to load. The Sheet can be found here:
>https://docs.google.com/spreadsheets/d/1jNV4aHMHj4AjCj3ued51Rr4nHZYEyfkGOwk1KuW3huE/edit?usp=sharing

### TODO in the future:
- Add more characters and games.
- Add more types of info besides stats.
- Make better use of the screen with more data.

### Progress:
- Oct 16: Started project after a brief bit of planning.
- Oct 18: Started attempting to implement the data using a json library.
- Oct 20: Continued work on json reading and formatting.
- Oct 22: Switched focus, decided to use RecylerView to display data and Google Sheets to store our info.
- Oct 23: Implemented Google Sheets API, read-only.
- Oct 25: Added second activity and transitions between the two activities.
- Oct 27: Finalized project. Finished RecyclerView, touched up appearance, and implemented proper Google Sheets parsing.
