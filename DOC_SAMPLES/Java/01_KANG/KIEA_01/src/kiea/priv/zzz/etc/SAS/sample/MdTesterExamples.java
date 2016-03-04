package kiea.priv.zzz.etc.SAS.sample;
//package com.sas.metadata.remote.test;

/**
 * Copyright (c) 2009 by SAS Institute Inc., Cary, NC 27513
 */

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sas.metadata.remote.AssociationList;
import com.sas.metadata.remote.CMetadata;
import com.sas.metadata.remote.Column;
import com.sas.metadata.remote.MdException;
import com.sas.metadata.remote.MdFactory;
import com.sas.metadata.remote.MdFactoryImpl;
import com.sas.metadata.remote.MdOMIUtil;
import com.sas.metadata.remote.MdOMRConnection;
import com.sas.metadata.remote.MdObjectStore;
import com.sas.metadata.remote.MetadataObjects;
import com.sas.metadata.remote.PhysicalTable;
import com.sas.metadata.remote.TextStore;


/**
 * This is a test class that contains examples for the SAS Java Metadata Interface.
 */
public class MdTesterExamples
{

   /**
    * The following statements instantiate the object factory.
    */
   private MdFactory _factory = null;

   /**
    * Default constructor
    */
   public MdTesterExamples()
   {
      // Calls the factory's constructor
      initializeFactory();
   }

   private void initializeFactory()
   {
      try
      {
         // Initializes the factory.  The Boolean parameter is used to determine  
         // if the application is running in a remote or local environment.  If  
         // the data does not need to be accessible across remote JVMs, then 
         // "false" can be used, as shown here.
         _factory = new MdFactoryImpl(false);
         
         // Defines debug logging, but does not turn it on.
         boolean debug = false;
         if (debug)
         {
            _factory.setDebug(false);
            _factory.setLoggingEnabled(false);
            
            // Sets the output streams for logging.  The logging output can be  
            // directed to any OutputStream, including a file.
            _factory.getUtil().setOutputStream(System.out);
            _factory.getUtil().setLogStream(System.out);
         }
         
         // To be notified of changes that have been persisted to the 
         // SAS Metadata Server within this factory (this includes adding objects,  
         // updating objects, and deleting objects), we can add a listener to the  
         // factory here. See MdFactory.addMdFactoryListener().
         // A listener is not needed for this example.
      }
      catch (Exception e)
      {
         e.printStackTrace();   
      }
   }
   
   /**
    * The following statements define variables for SAS Metadata Server
    * connection properties, instantiate a connection factory, issue
    * the makeOMRConnection method, and check exceptions for error conditions. 
    * 
    */
   public boolean connectToServer()
   {
      String serverName = "MACHINE_NAME";
      String serverPort = "8561";
      String serverUser = "USERNAME";
      String serverPass = "PASSWORD";
      
      
      try
      {
         MdOMRConnection connection = _factory.getConnection();
 
        
         // This statement makes the connection to the server.
         connection.makeOMRConnection(
                serverName, 
                serverPort, 
                serverUser, 
                serverPass
                );
     
         // The following statements define error handling and error 
         // reporting messages.
      }
      catch (MdException e)
      {
         Throwable t = e.getCause();
         if (t != null)
         {
            String ErrorType = e.getSASMessageSeverity();
            String ErrorMsg = e.getSASMessage();
            if (ErrorType == null)
            {
               // If there is no SAS server message, write a Java/CORBA message.
            }
            else
            {
               // If there is a message from the server:
               System.out.println(ErrorType + ": " + ErrorMsg);
            }
            if (t instanceof org.omg.CORBA.COMM_FAILURE)
            {
               // If there is an invalid port number or host name:
               System.out.println(e.getLocalizedMessage());
            }
            else if (t instanceof org.omg.CORBA.NO_PERMISSION)
            {
               // If there is an invalid user ID or password:
               System.out.println(e.getLocalizedMessage());
            }
         }
         else
         {
            // If we cannot find a nested exception, get message and print.
            System.out.println(e.getLocalizedMessage());
         }
         // If there is an error, print the entire stack trace.
         e.printStackTrace();
         return false;
      }
      catch (RemoteException e)
      {
         // Unknown exception.
         e.printStackTrace();
         return false;
      }
      // If no errors occur, then a connection is made.
      return true;
   }

   /**
    * This example displays the status of the SAS Metadata Server.
    */
   public void displayServerInformation()
   {
      try
      {
         MdOMRConnection connection = _factory.getConnection();
                  
         // Check the status of the metadata server
         System.out.println("\nGetting server status...");
         int status = connection.getServerStatus();
         switch (status)
         {
            case MdOMRConnection.SERVER_STATUS_OK:
               System.out.println("Server is running");
               break;
            case MdOMRConnection.SERVER_STATUS_PAUSED:
               System.out.println("Server is paused");
               break;
            case MdOMRConnection.SERVER_STATUS_ERROR:
               System.out.println("Server is not running");
               break;
         }
         
         // Check the version of the SAS Metadata Server
         int version = connection.getPlatformVersion();
         System.out.println("Server platform version: " + version);         
      }
      catch (MdException e)
      {
         e.printStackTrace();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * This example retrieves a list of the repositories that are registered 
    * on the SAS Metadata Server. 
    * @return the list of available repositories (list of CMetadata objects)
    */
   public List getRepositories()
   {
      try
      {
         // Repositories are listed with the getRepositories method.
         System.out.println("\nThe repositories contained on this server are: ");
         
         MdOMIUtil omiUtil = _factory.getOMIUtil();
         List reposList = omiUtil.getRepositories();
         Iterator iter = reposList.iterator();
         while (iter.hasNext())
         {
            // Print the Name= and Id= of each repository.
            CMetadata repository = (CMetadata) iter.next();
            System.out.println("Repository: " + repository.getName() + " 
                               (" + repository.getFQID() +")");
         }
         return reposList;
      }
      catch (MdException e)
      {
         e.printStackTrace();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
      return new ArrayList();
   }

   /**
    * This example lists the metadata types available on the SAS Metadata Server
    * and their descriptions.
    */
   public void displayMetadataTypes()
   {
      try
      {
         // Metadata types are listed with the getTypes method.
         System.out.println("\nThe object types contained on this server are: ");
         List nameList = new ArrayList(100);
         List descList = new ArrayList(100);
         _factory.getOMIUtil().getTypes(nameList, descList);
         Iterator iter1 = nameList.iterator();
         Iterator iter2 = descList.iterator();
         while (iter1.hasNext() && iter2.hasNext())
         {
            // Print the name and description of each metadata object type
            String name = (String) iter1.next();
            String desc = (String) iter2.next();
            System.out.println("Type: " + name + ", desc: " + desc);
         }
      }
      catch (MdException e)
      {
         e.printStackTrace();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * This example creates a table, column, and a note on the column.
    * @param Repository CMetadata object with id of form:  A0000001.A5KHUI98
    */
   public void createTable(CMetadata repository)
   {
      if (repository != null)
      {
         try
         {
            System.out.println("\nCreating objects on the server...");
            
            // We have a repository object.
            // We use the reposFQID method to get its fully qualified ID.
            String reposFQID = repository.getFQID();

            // We need the short repository ID to create an object.
           String shortReposID = reposFQID.substring(reposFQID.indexOf(".") + 1, 
                                    reposFQID.length());

            // Now we create an object store to hold all our objects.  
            // This will be used to maintain a list of objects to persist
            // to the server.
            MdObjectStore store = _factory.createObjectStore();

            // Create a PhysicalTable object named "TableTest".
            PhysicalTable table=(PhysicalTable)_factory.createComplexMetadataObject(
                     store,
                     null,
                     "TableTest",
                     MetadataObjects.PHYSICALTABLE,
                     shortReposID
                     );

            // Create a Column named "ColumnTest".
            Column column = (Column) _factory.createComplexMetadataObject(
                     store,
                     null,
                     "ColumnTest",
                     MetadataObjects.COLUMN,
                     shortReposID
                     );

            // Set the attributes of the column.
            column.setColumnName("MyTestColumnName");
            column.setSASColumnName("MyTestSASColumnName");
            column.setDesc("This is a description of a column");

            // Use the get"AssociationName"() method to associate the column with  
            // the table. This method creates an AssociationList object for the
            // table object. The inverse association will be created automatically.
            // The add(MetadataObject) method adds myColumn to the AssociationList.
            table.getColumns().add(column);

            // Create a note for the column named "NoteTest".
            TextStore note = (TextStore) _factory.createComplexMetadataObject(
                     store,
                     null,
                     "NoteTest",
                     MetadataObjects.TEXTSTORE,
                     shortReposID
                     );

            // Set the StoredText= attribute for the note
            note.setStoredText("Information about the note");
            
            // Associate the note with the column.
            column.getNotes().add(note);

            // Now, persist all of these changes to the server
            table.updateMetadataAll();

            // When finished, clean up the objects in the store if they 
            // are no longer being used.
            store.dispose();
         }
         catch (MdException e)
         {
            e.printStackTrace();
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
         }      
      }
   }

   /**
    * This example reads the newly created objects from the server.
    * @param repository identifies the repository from which to read our objects.
    */
   public void readTable(CMetadata repository)
   {
      if(repository != null)
      {
         try
         {
            System.out.println("\nReading objects from the server...");

            // First we create an MdObjectStore as a container for the 
            // objects that we will create/read/persist to the server as 
            // one collection.
            MdObjectStore store = _factory.createObjectStore();

            // The following statements define GetMetadataObjectsSubset options 
            // strings. These XML strings are used in conjunction with SAS Open  
            // Metadata Interface flags. The <XMLSELECT> element specifies  
            // filter criteria. The <TEMPLATES> element specifies the metadata   
            // properties to be returned for each object from the server.
            String xmlSelect = "<XMLSELECT Search=\"@Name='TableTest'\"/>";
            String template = 
               "<Templates>" + 
                  "<PhysicalTable Id=\"\" Name=\"\" Desc=\"\">" +
                    "<Columns/>" +
                  "</PhysicalTable>" +
                  "<Column Id=\"\" Name=\"\" Desc=\"\" ColumnName=\"\">" + 
                     "<Notes/>" + 
                  "</Column>" +
                  "<TextStore Id=\"\" Name=\"\" Desc=\"\" StoredText=\"\"/>" +
                "</Templates>";
            
            // Add the xmlSelect and template strings together
            String sOptions = xmlSelect + template; 

            // The following statements go to the server with a fully qualified 
            // repository ID and specify the type of object we are searching for 
            // (MdObjectFactory.PHYSICALTABLE) using the OMI_XMLSELECT, 
            // OMI_TEMPLATE, and OMI_GET_METADATA flags. OMI_GET_METADATA 
            // tells the server to get all of the attributes specified in the template
            // for each object that  is returned. The table, column, and note will be
            // read from the server and created within the specified object store.
            int flags = MdOMIUtil.OMI_XMLSELECT | MdOMIUtil.OMI_TEMPLATE | 
                           MdOMIUtil.OMI_GET_METADATA;
            List tableList = _factory.getOMIUtil().getMetadataObjectsSubset(
                    store, 
                    repository.getFQID(),
                    MetadataObjects.PHYSICALTABLE,
                    flags,
                    sOptions
                    );
           
 Iterator iter = tableList.iterator();
            while (iter.hasNext())
            {
               // Print the Id=  and Name= of the table returned from the server
               PhysicalTable table = (PhysicalTable) iter.next();
               System.out.println("Found table: " + table.getName() + " 
                                    (" + table.getId() + ")");

               // Get the columns for this table.
               AssociationList columns = table.getColumns();
               for (int i = 0; i < columns.size(); i++)
               {
                  // Print the Id= and Name= of associated columns
                  Column column = (Column) columns.get(i);
                  System.out.println("Found column: " + column.getName() + " 
                                         (" + column.getId() + ")");
                       System.out.println("\tDescription: " + column.getDesc());
                  System.out.println("\tColumnName: " + column.getColumnName());
                  
                  // Get notes associated with the columns.
                  AssociationList notes = column.getNotes();
                  for (int j = 0; j < notes.size(); j++)
                  {
                     // Print the Id=, Name=, and StoredText= values of associated
                    //  notes
                     TextStore note = (TextStore) notes.get(j);
                     System.out.println("Found textstore: " + note.getName() + " 
                              ("+ note.getId() + ")");
                            System.out.println("\tStoredText: " + note.getStoredText());
                  }
               }
            }

            // When finished, clean up the objects in the store if they 
            // are no longer being used.
            store.dispose();
         }
         catch (MdException e)
         {
            e.printStackTrace();
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * This example deletes the objects that we created.
    * @param repository
    */
   public void deleteTable(CMetadata repository)
   {
      if(repository != null)
      {
         try
         {
            System.out.println("\nDeleting the objects from the server...");
            MdObjectStore store = _factory.createObjectStore();
            
            // Create a list of the objects that need to be deleted
            // from the server.
            List deleteList = new ArrayList();
            
            // Query for the table again
            String xmlSelect = "<XMLSELECT Search=\"@Name='TableTest'\"/>";
            String template = "<Templates>" + 
                                 "<PhysicalTable>" +
                                   "<Columns/>" +
                                 "</PhysicalTable>" +
                                 "<Column>" + 
                                    "<Notes/>" + 
                                 "</Column>" +
                               "</Templates>";
            
            // Add the xmlSelect and template strings together
            String sOptions = xmlSelect + template; 
            
            int flags = MdOMIUtil.OMI_XMLSELECT | MdOMIUtil.OMI_TEMPLATE | 
                           MdOMIUtil.OMI_GET_METADATA;
            List tableList = _factory.getOMIUtil().getMetadataObjectsSubset(
                         store, 
                         repository.getFQID(),
                         MetadataObjects.PHYSICALTABLE,
                         flags,
                         sOptions
                         );

            
            
            // Add the found objects to the delete list
            Iterator iter = tableList.iterator();
            while (iter.hasNext())
            {
               PhysicalTable table = (PhysicalTable) iter.next();
               deleteList.add(table);
               
               // Get the columns
               AssociationList columns = table.getColumns();
               for (int i = 0; i < columns.size(); i++)
               {
                  Column column = (Column) columns.get(i);
                  deleteList.add(column);
                  
                  // Get the notes
                  AssociationList notes = column.getNotes();
                  for (int j = 0; j < notes.size(); j++)
                  {
                     TextStore note = (TextStore) notes.get(j);
                     deleteList.add(note);
                  }
               }
            }

            // Delete everything that is in the delete list
            if (deleteList.size() > 0)
            {
               System.out.println("Deleting " + deleteList.size() + " objects");
               _factory.deleteMetadataObjects(deleteList);
            }
            
            // When finished, clean up the objects in the store if they 
            // are no longer being used.
            store.dispose();
         }
         catch (MdException e)
         {
            e.printStackTrace();
         }
         catch (RemoteException e)
         {
            e.printStackTrace();
         }
      }
   }

   /**
    * This example displays the PhysicalTable objects in a repository. 
    * @param repository CMetadata identifies the repository from which to read
    * the objects.
    */
   public void displayAllTables(CMetadata repository)
   {
      try
      {
         // Print a descriptive message about the request.
         System.out.println("\nRetrieving all PhysicalTable objects contained in " 
               + " repository " + repository.getName());

         // Use the short repository ID to pass in the method.
         String reposID = repository.getFQID();

         // We get a list of PhysicalTable objects.
         MdObjectStore store = _factory.createObjectStore();
         
         // Use the OMI_ALL_SIMPLE flag to get all attributes for each table returned
         int flags = MdOMIUtil.OMI_GET_METADATA | MdOMIUtil.OMI_ALL_SIMPLE;
         List tables = _factory.getOMIUtil().getMetadataObjectsSubset(
                store,
                reposID,                         // Repository to search
                MetadataObjects.PHYSICALTABLE,   // Metadata type to search for
                flags,
                "" 
                );

         // Print information about them.
         Iterator iter = tables.iterator();
         while( iter.hasNext())
         {
            PhysicalTable ptable = (PhysicalTable)iter.next();
            System.out.println("PhysicalTable: " + ptable.getName() + ", 
                               " + ptable.getFQID() +", 
                               " + ptable.getDesc());
         }
        
 
         // When finished, clean up the objects in the store if they 
         // are no longer being used.
         store.dispose();
      }
      catch (MdException e)
      {
         e.printStackTrace();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * This example gets information for a specific 
    * PhysicalTable object.
    * @param table the table to retrieve
    */
   public void getTableInformation(PhysicalTable table)
   {
      try
      {
         // Print a descriptive message about the request.
         System.out.println("\nGetting information for a specific PhysicalTable");

         // Create a template to get detailed information for this table
         String template = "<Templates>" + 
                              "<PhysicalTable>" +
                                "<Columns/>" +
                                "<Notes/>" +
                                "<Keywords/>" +
                              "</PhysicalTable>" +
                            "</Templates>";
         
         // Use the OMI_ALL_SIMPLE flag to get all of the table's attributes.
         int flags = MdOMIUtil.OMI_GET_METADATA | MdOMIUtil.OMI_ALL_SIMPLE |
                        MdOMIUtil.OMI_TEMPLATE;
         table = (PhysicalTable) _factory.getOMIUtil().getMetadataAllDepths(
               table,
               null,
               null,
               template,
               flags
               );

         // Print information about the table
         System.out.println("Table attributes: ");
         System.out.println("Name = " + table.getName());
         System.out.println("Id = " + table.getId());
         System.out.println("Description = " + table.getDesc());
         System.out.println("Created Date = " + table.getMetadataCreated());
         System.out.println("Table associations: ");
         System.out.println("Number of Columns = " + table.getColumns().size());
         System.out.println("Number of Keywords = " + table.getKeywords().size());
         System.out.println("Number of Notes = " + table.getNotes().size());
      }
      catch (MdException e)
      {
         e.printStackTrace();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
      }      
   }
   

   /**
    * The main method for the class
    */
   public static void main(String[] args)
   {
      MdTesterExamples tester = new MdTesterExamples();
      
      // Connect to the SAS Metadata Server
      boolean connected = tester.connectToServer();
      if(connected)
      {
         System.out.println("Connected...");
      }
      else
      {
         System.out.println("Error Connecting...");
         return;
      }
      
      // Now that we are connected, check the status of the server
      tester.displayServerInformation();
      
      // Get the list of repositories on the server
      List repositories = tester.getRepositories();
      CMetadata repos = (CMetadata) repositories.get(0);
      
      // Get the list of metadata types available on the server
      tester.displayMetadataTypes();
      
      // Create a new PhysicalTable object and add it to the server
      tester.createTable(repos);
      
      // Query for the PhysicalTable just added to the server
      tester.readTable(repos);
      
      // Delete the PhysicalTable
      tester.deleteTable(repos);
      
      System.exit(1);      
   }

}
