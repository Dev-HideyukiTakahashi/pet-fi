import { Pets } from "./pets";

export class Client {

  id!: number;
  name!: string;
  phone!: string;
  facebook!: string;
  instagram!: string;
  city!: string;
  pets: Pets[] = [];
}
