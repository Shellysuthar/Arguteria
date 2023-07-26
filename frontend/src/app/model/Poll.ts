import Options from "./Options";


export default interface Poll {
    id?: number;
    options: Options[];
    votedUsers?: Array<string>;
    title: string
    visible?: boolean;
    endDate: string;
    startDate?: string;
  }