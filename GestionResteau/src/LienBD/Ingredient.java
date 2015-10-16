/***********************************************************************
 * Module:  Ingredient.java
 * Author:  user
 * Purpose: Defines the Class Ingredient
 ***********************************************************************/

import java.util.*;

/** @pdOid 1e10422b-780b-45ff-9b0a-f5c88f9c767a */
public class Ingredient {
   /** @pdOid 04abefee-ecc8-426a-a4ef-c05876042e38 */
   private int numIngredient;
   /** @pdOid 5f8c23b5-eee8-4c0e-be94-6b6a61464276 */
   private int prixU;
   /** @pdOid 235f6f76-b6db-474f-ae76-12fb9ddf21a7 */
   private int stock;
   
   /** @pdRoleInfo migr=no name=EtatI assc=etatI mult=1..1 type=Aggregation */
   private EtatI etatI;

}