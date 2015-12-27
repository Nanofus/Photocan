package app.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.FileObject;
 
public interface GifRepository extends JpaRepository<FileObject, Long> {
 
}