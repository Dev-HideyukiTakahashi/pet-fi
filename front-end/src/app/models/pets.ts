import { Client } from "./client";

export class Pets {

  id!:number;
  name!:string;
  sex!:string;
  additionalInformation!:string;
  qrcode!:string;
  photo!:string;
  wanted!:boolean;
  petType!:string;
  client!:Client;
}
