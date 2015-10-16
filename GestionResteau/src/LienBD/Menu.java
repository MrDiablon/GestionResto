/***********************************************************************
 * Module:  Menu.java
 * Author:  user
 * Purpose: Defines the Class Menu
 ***********************************************************************/

import java.util.*;

/** @pdOid e329a408-5a39-4019-9e63-56d7d0e73edb */
public class Menu {
   /** @pdOid 9f3d2a00-7262-431c-9d46-34127c8659db */
   private int numMenu;
   /** @pdOid 7e4589f9-5213-4f20-830a-61ecf1558d94 */
   private String composition;
   /** @pdOid e0a3dcf3-b8cc-4dcf-be59-4d9ac99677e1 */
   private String nom;
   
   /** @pdRoleInfo migr=no name=Plat assc=composer coll=java.util.Collection impl=java.util.ArrayList mult=0..* type=Composition */
   private java.util.Collection<Plat> plat;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Plat> getPlat() {
      if (plat == null)
         plat = new java.util.ArrayList<Plat>();
      return plat;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPlat() {
      if (plat == null)
         plat = new java.util.ArrayList<Plat>();
      return plat.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPlat */
   public void setPlat(java.util.Collection<Plat> newPlat) {
      removeAllPlat();
      for (java.util.Iterator iter = newPlat.iterator(); iter.hasNext();)
         addPlat((Plat)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPlat */
   public void addPlat(Plat newPlat) {
      if (newPlat == null)
         return;
      if (this.plat == null)
         this.plat = new java.util.ArrayList<Plat>();
      if (!this.plat.contains(newPlat))
         this.plat.add(newPlat);
   }
   
   /** @pdGenerated default remove
     * @param oldPlat */
   public void removePlat(Plat oldPlat) {
      if (oldPlat == null)
         return;
      if (this.plat != null)
         if (this.plat.contains(oldPlat))
            this.plat.remove(oldPlat);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPlat() {
      if (plat != null)
         plat.clear();
   }

}