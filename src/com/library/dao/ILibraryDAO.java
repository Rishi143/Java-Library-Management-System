package com.library.dao;

import com.library.vo.Librarian;
import com.library.vo.Student;

public interface ILibraryDAO {
	public boolean insertStudent(Student student);
	public boolean libraryStudent(Librarian libraian);
}
