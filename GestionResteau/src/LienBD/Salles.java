/***********************************************************************
 * Module:  Salles.java
 * Author:  user
 * Purpose: Defines the Class Salles
 ***********************************************************************/

import java.util.*;

/** @pdOid 0ceaeea8-345e-4b74-a07e-16dda88a0b4b */
public class Salles {
   /** @pdOid 3b960ee8-a25e-42e8-9e96-011018a26643 */
   private int numSalle;
   /** @pdOid fbeab2f0-184b-49ea-9768-21ccced9374f */
   private int numResto;
   /** @pdOid 99ba36da-823e-4c82-b649-6b21e5a046a7 */
   private String nomSalle;
   /** @pdOid 2db099b8-e177-4104-8b39-18e8490a1b1f */
   private int nombreTables;
   
   /** @pdRoleInfo migr=no name=Etat assc=etat mult=1..1 type=Aggregation */
   private Etat etat;
   /** @pdRoleInfo migr=no name=Restaurant assc=resto mult=1..1 type=Composition */
   private Restaurant restaurant;
   /** @pdRoleInfo migr=no name=Menu assc=servir coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   private java.util.Collection<Menu> menu;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Menu> getMenu() {
      if (menu == null)
         menu = new java.util.HashSet<Menu>();
      return menu;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMenu() {
      if (menu == null)
         menu = new java.util.HashSet<Menu>();
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
         this.menu = new java.util.HashSet<Menu>();
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

}