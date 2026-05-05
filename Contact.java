class Contact {
    String name;
    long Phone;
    Contact (String name, long Phone){
        this.name =name;
        this.Phone = Phone;
    }
    void display(){
        System.out.println("Naam:" + name + " | Number:" + Phone);
    }
}
