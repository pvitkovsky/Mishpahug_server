export class Choice {
  name: string;
  variants : string[]
  constructor(choice : string, variants : string[]){
    this.name = choice;
    this.variants = variants;
  }
}

