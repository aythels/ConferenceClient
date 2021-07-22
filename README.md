Dependencies to download if not already downloaded:
1. Download itextpdf JAR https://jar-download.com/artifacts/com.itextpdf/itextpdf/5.5.9/source-code
2. Download JavaFX https://openjfx.io/

Run main in AppGUI.

List of features implemented(Mandatory):

1.There will now be many types of events. A one-speaker event is the same as a "talk" from Phase 1. You can have multi-speaker events,
like a panel discussion, and no-speaker events, like a party. Events can last as long as you want. You can measure the duration
of an event in hours, or minutes. You get to decide.

2. Events can be canceled by at least one organizer.

3. At least one more type of user will be included in your program. For example, an Admin user who can delete messages or
events with no attendees, or a VIP user who can access VIP-only events.

4. Organizers can also create Speaker accounts, Attendee accounts, and any other type of user accounts that are in your program.

5. Each event has a maximum number of people who can attend it. This amount can be set when the event is created and also changed later, by an organizer.
Your program should check the maximum capacity for the room where the event will be held, and prevent the number of attendees from going above the room's capacity.

List of features implemented(Optional):

1. Have the program produce a neatly formatted program or schedule for the conference that users have the option
of "downloading" (outputting it as html, pdf, or similar). Alternatively, if you just want the program to print
the schedule to the screen, then users should be able to request a schedule by at least three of: day, by speaker,
by time (all 3-4 pm talks on all days), or just the ones they have signed up for, or "liked" events (where you have
to enable users to "like" events).

2. Replace your text UI with a Graphic User Interface (GUI), which should follow the Model-View-Presenter architecture.
