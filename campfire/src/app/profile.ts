export interface IProfile {
    cf_User_UserId: number;
    cf_User_Username: string;
    cf_User_Password: string; //will set to null upon fetching as we don't want to bring it from DB
    cf_User_Title: string;
    cf_User_Fname: string;
    cf_User_Lname: string;
    cf_User_Email: string;
    cf_User_Birthday: Date;
    cf_User_ProfilePic: string;
    cf_User_Bio: string;
}
