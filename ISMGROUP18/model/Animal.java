package model;

public class Animal {

  private int id;
  private String age;
  private String colour;
  private String sex;
  private String species;
  private String comments;
  private Reservation reservation;
  private String imagePath; //
  private Organization organization;
  private String race;

  public Animal() {

  }

  public Animal(int id, String age, String colour, String sex, String species, String comments, String imagePath, String race) {
    this.id = id;
    this.age = age;
    this.colour = colour;
    this.sex = sex;
    this.species = species;
    this.comments = comments;
    this.imagePath = imagePath;
    this.race = race;
  }

  public int getId() {
    return id;
  }

  public Animal setId(int id) {
    this.id = id;
    return this;
  }

  public String getSpecies() {
    return species;
  }

  public Animal setSpecies(String species) {
    this.species = species;
    return this;
  }

  public String getColour() {
    return colour;
  }

  public Animal setColour(String colour) {
    this.colour = colour;
    return this;
  }

  public String getAge() {
    return age;
  }

  public Animal setAge(String age) {
    this.age = age;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public Animal setComments(String comments) {
    this.comments = comments;
    return this;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public Animal setReservation(Reservation reservation) {
    this.reservation = reservation;
    return this;
  }

  public Organization getOrganization() {
    return organization;
  }

  public Animal setOrganization(Organization organization) {
    this.organization = organization;
    return this;
  }

  public String getImagePath() {
    return imagePath;
  }

  public Animal setImagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }

  public String getRace() {
    return race;
  }

  public Animal setRace(String race) {
    this.race = race;
    return this;
  }

  public String getSex() {
    return sex;
  }

  public Animal setSex(String sex) {
    this.sex = sex;
    return this;
  }
}
