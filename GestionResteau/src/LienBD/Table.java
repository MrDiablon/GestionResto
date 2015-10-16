/***********************************************************************
 * Module:  Table.java
 * Author:  user
 * Purpose: Defines the Class Table
 ***********************************************************************/

import java.util.*;

/** @pdOid a46f22a2-3d4e-47c8-b88f-3c23b3a924dd */
public class Table {
   /** @pdOid 7ed446a4-84a1-48b9-853e-13d1c532a996 */
   private int numTable;
   /** @pdOid 2d534a85-e577-4aa8-b7be-8a7072a41984 */
   private int numSalle;
   /** @pdOid ebc77833-eb99-45a4-984b-8cfa32546211 */
   private int capacite;
   /** @pdOid 69fa993a-db99-40ad-b23b-32daea7df929 */
   private int posX;
   /** @pdOid 50d8e60f-8a43-4d56-921a-abbe725228cd */
   private int posY;
   
   /** @pdRoleInfo migr=no name=Etat assc=etat mult=1..1 type=Aggregation */
   private Etat etat;
   /** @pdRoleInfo migr=no name=Salles assc=salle mult=0..1 type=Composition */
   private Salles salles;
   /** @pdRoleInfo migr=no name=Menu assc=commander coll=java.util.Collection impl=java.util.ArrayList mult=0..* type=Aggregation */
   private java.util.Collection<Menu> menu;
   /** @pdRoleInfo migr=no name=Reservation assc=reserver coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   private java.util.Collection<Reservation> reservation;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Menu> getMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMenu */
   public void setMenu(java.util.Collection<Menu> newMenu) {
      removeAllMenu();
      for (java.util.Iterator iter = newMenu.iterator(); iter.hasNext();)
         addMenu((Menu)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMenu */
   public void addMenu(Menu newMenu) {
      if (newMenu == null)
         return;
      if (this.menu == null)
         this.menu = new java.util.ArrayList<Menu>();
      if (!this.menu.contains(newMenu))
         this.menu.add(newMenu);
   }
   
   /** @pdGenerated default remove
     * @param oldMenu */
   public void removeMenu(Menu oldMenu) {
      if (oldMenu == null)
         return;
      if (this.menu != null)
         if (this.menu.contains(oldMenu))
            this.menu.remove(oldMenu);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllMenu() {
      if (menu != null)
         menu.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Reservation> getReservation() {
      if (reservation == null)
         reservation = new java.util.HashSet<Reservation>();
      return reservation;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorReservation() {
      if (reservation == null)
         reservation = new java.util.HashSet<Reservation>();
      return reservation.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newReservation */
   public void setReservation(java.util.Collection<Reservation> newReservation) {
      removeAllReservation();
      for (java.util.Iterator iter = newReservation.iterator(); iter.hasNext();)
         addReservation((Reservation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newReservation */
   public void addReservation(Reservation newReservation) {
      if (newReservation == null)
         return;
      if (this.reservation == null)
         this.reservation = new java.util.HashSet<Reservation>();
      if (!this.reservation.contains(newReservation))
         this.reservation.add(newReservation);
   }
   
   /** @pdGenerated default remove
     * @param oldReservation */
   public void removeReservation(Reservation oldReservation) {
      if (oldReservation == null)
         return;
      if (this.reservation != null)
         if (this.reservation.contains(oldReservation))
            this.reservation.remove(oldReservation);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllReservation() {
      if (reservation != null)
         reservation.clear();
   }

}