/** 
Edit movies. An edit is an 'add', 'change', or 'delete'.

<P>The flow is as follows:
<PRE>{@link hirondelle.movies.main.MainWindow}
  -> {@link CihazAraAction.movies.edit.MovieActionAdd} (simple Swing Action) - similar for Change
    -> {@link CihazAraView.movies.edit.MovieView} (dialog)
      -> {@link CihazAraController.movies.edit.MovieController}
        -> {@link Uretici.movies.edit.Movie} (model) 
        -> {@link UreticiDAO.movies.edit.MovieDAO} (data access)</PRE>

The delete operation does not proceed through the controller. Many would prefer to 
change that. 
*/
package com.zk.cw.edit;