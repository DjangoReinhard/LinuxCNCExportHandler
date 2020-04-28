package de.schwarzrot.linuxcnc;
/* 
 * **************************************************************************
 * 
 *  file:       LinuxCNCExportHandler.java
 *  project:    GUI for linuxcnc
 *  subproject: exporthandler for linuxcnc
 *  purpose:    exporthandler that writes tooltable in format used
 *  created:    4.12.2019 by Django Reinhard
 *  copyright:  all rights reserved
 * 
 *  This program is free software: you can redistribute it and/or modify 
 *  it under the terms of the GNU General Public License as published by 
 *  the Free Software Foundation, either version 2 of the License, or 
 *  (at your option) any later version. 
 *   
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU General Public License for more details. 
 *   
 *  You should have received a copy of the GNU General Public License 
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * **************************************************************************
 */

import java.io.PrintWriter;

import de.schwarzrot.linuxcnc.data.CategoryInfo;
import de.schwarzrot.linuxcnc.data.LibInfo;
import de.schwarzrot.linuxcnc.data.ToolInfo;
import de.schwarzrot.linuxcnc.export.IExportHandler;


public class LinuxCNCExportHandler implements IExportHandler {
   @Override
   public void closeCategory(CategoryInfo catInfo) throws Exception {
      // do nothing: linuxcnc has no categories
   }


   @Override
   public void closeLibrary(LibInfo libInfo) throws Exception {
      pw.close();
   }


   @Override
   public void closeTool(ToolInfo toolInfo) throws Exception {
      // do nothing: tool have no children
   }


   @Override
   public void openCategory(CategoryInfo catInfo) throws Exception {
      // do nothing: linuxcnc has no categories
   }


   @Override
   public void openLibrary(LibInfo libInfo, String fileName) throws Exception {
      if (!fileName.endsWith(".tbl")) fileName = fileName + ".tbl";
      pw = new PrintWriter(fileName);
   }


   @Override
   public void openTool(ToolInfo toolInfo) throws Exception {
      StringBuilder sb = new StringBuilder();

      sb.append("T");
      sb.append(toolInfo.getToolNumber());
      sb.append(" P");
      // TODO: can Linuxcnc live with all slots being 0?
      sb.append(++slotNumber);
      sb.append(" Z");
      sb.append(toolInfo.getColletLength() + toolInfo.getFreeLength());
      sb.append(" D");
      sb.append(toolInfo.getFluteDiameter());
      sb.append(" ;");
      sb.append(toolInfo.getToolName());

      String out = sb.toString();

      // System.err.println(out);
      pw.println(out);
   }

   private int         slotNumber;
   private PrintWriter pw;
}
