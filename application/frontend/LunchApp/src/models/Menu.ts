export type Menu = {
    id : Number;
    name: String;
    price: Number;
    createdAt: Date;
    tuition: Tuition;
}

type Tuition = {
    cal : Number;
    protein: Number;
    fat: Number;
    carbo: Number;
    salt: Number;
}