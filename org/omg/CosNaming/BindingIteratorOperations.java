package org.omg.CosNaming;






public interface BindingIteratorOperations 
{

  
  boolean next_one (org.omg.CosNaming.BindingHolder b);

  
  boolean next_n (int how_many, org.omg.CosNaming.BindingListHolder bl);

  
  void destroy ();
} 
