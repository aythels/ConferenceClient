package api.controllers;

/**
 * A class that inherits Controller contains various methods intended for the client to call.
 *
 * Controller classes can inherit other controller classes, hence hierarchically inheriting their methods. For example,
 * if OrganizerController inherits AttendeeController, OrganizerController contains all of AttendeeController's methods
 * in addition to it's own methods.
 *
 * The naming and organization of Controller classes serve no purpose other than readability. They are manually mapped
 * to user types in the initializeAPI class to determine what user types have access to which controllers, according to
 * the needs of the API class.
 */

public abstract class Controller {
    protected Controller(){
    }

}