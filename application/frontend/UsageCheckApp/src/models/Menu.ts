export type Menu = {
    id : Number;
    name: String;
    price: Number;
    createdAt: Date;
    tuition: Tuition;
}

export type Tuition = {
    cal : Number;
    protein: Number;
    fat: Number;
    carbo: Number;
    salt: Number;
}