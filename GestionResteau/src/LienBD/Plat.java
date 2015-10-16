/***********************************************************************
 * Module:  Plat.java
 * Author:  user
 * Purpose: Defines the Class Plat
 ***********************************************************************/

import java.util.*;

/** @pdOid c20cb882-9180-4a5b-8442-151e2c858fc2 */
public class Plat {
   /** @pdOid f41255c6-ab42-4c05-9cd4-17a08756557a */
   private int numPlat;
   /** @pdOid 7d15315c-cd77-4d01-b5bf-25adc216256f */
   private String recette;
   /** @pdOid d51b5ea4-9c0c-4357-b550-95647bea15e7 */
   private int prixU;
   
   /** @pdRoleInfo migr=no name=Ingredient assc=association12 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   private java.util.Collection<Ingredient> ingredient;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Ingredient> getIngredient() {
      if (ingredient == null)
         ingredient = new java.util.HashSet<Ingredient>();
      return ingredient;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorIngredient() {
      if (ingredient == null)
         ingredient = new java.util.HashSet<Ingredient>();
      return ingredient.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newIngredient */
   public void setIngredient(java.util.Collection<Ingredient> newIngredient) {
      removeAllIngredient();
      for (java.util.Iterator iter = newIngredient.iterator(); iter.hasNext();)
         addIngredient((Ingredient)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newIngredient */
   public void addIngredient(Ingredient newIngredient) {
      if (newIngredient == null)
         return;
      if (this.ingredient == null)
         this.ingredient = new java.util.HashSet<Ingredient>();
      if (!this.ingredient.contains(newIngredient))
         this.ingredient.add(newIngredient);
   }
   
   /** @pdGenerated default remove
     * @param oldIngredient */
   public void removeIngredient(Ingredient oldIngredient) {
      if (oldIngredient == null)
         return;
      if (this.ingredient != null)
         if (this.ingredient.contains(oldIngredient))
            this.ingredient.remove(oldIngredient);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIngredient() {
      if (ingredient != null)
         ingredient.clear();
   }

}