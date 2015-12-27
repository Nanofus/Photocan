package app.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.FileObject;
 
public interface ImageRepository extends JpaRepository<FileObject, Long> {
 
}