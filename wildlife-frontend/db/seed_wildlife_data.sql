-- Seed data for wildlife conservation system
-- Keep zones low and add enough records to populate all modules.
-- Adjust table names to match your backend schema if they differ.

DELETE FROM rescues;
DELETE FROM incidents;
DELETE FROM patrols;
DELETE FROM animals;
DELETE FROM species;
DELETE FROM habitats;
DELETE FROM resources;
DELETE FROM conservation_programs;
DELETE FROM staff;
DELETE FROM zones;
DELETE FROM protected_areas;

INSERT INTO protected_areas (id, name, location, area_size, description) VALUES
  (1, 'Serengeti Plains', 'Northern Tanzania', 450000, 'Open savannah ecosystem with large migratory herbivores.'),
  (2, 'Kibale Forest', 'Western Uganda', 79500, 'Dense tropical rainforest and primate sanctuary.'),
  (3, 'Nairobi National Park', 'Central Kenya', 11700, 'Urban protected area with diverse wildlife near the capital.'),
  (4, 'Okavango Delta', 'Botswana', 550000, 'Wetland mosaic with rich bird and megafauna diversity.'),
  (5, 'Kruger South', 'South Africa', 190000, 'Savannah reserve with high predator activity and anti-poaching patrols.');

INSERT INTO zones (id, zone_name, protected_area_id, description) VALUES
  (1, 'Central Grassland', 1, 'Open plains with grazing migration routes.'),
  (2, 'River Basin', 2, 'Forest river corridors and primate movement paths.'),
  (3, 'Athi Basin', 3, 'Lowland habitat surrounding key migration routes.'),
  (4, 'Papyrus Marsh', 4, 'Wetland edge and floodplain zone.'),
  (5, 'Crocodile Ridge', 5, 'High-visibility predator territory with heavy patrol activity.');

INSERT INTO habitats (id, habitat_name, zone_id, condition, description) VALUES
  (1, 'Savannah', 1, 'Healthy', 'Mixed grassland with seasonal grazing pressure.'),
  (2, 'Riverine Forest', 2, 'Stable', 'Dense tree cover with water access for mammals.'),
  (3, 'Open Woodland', 3, 'Fair', 'Mixed woodland for herbivores and predators.'),
  (4, 'Marshland', 4, 'Healthy', 'Floodplain habitat supporting birds and aquatic animals.'),
  (5, 'Rocky Ridge', 5, 'Stable', 'Predator movement zone with strong visibility.'),
  (6, 'Acacia Belt', 1, 'Healthy', 'Acacia-dominated grazing corridor.'),
  (7, 'Canopy Forest', 2, 'Fair', 'Dense forest interior with canopy nesting sites.'),
  (8, 'Wetland Edge', 4, 'Stable', 'Transition between marsh and dry grassland.'),
  (9, 'Dry Creek', 5, 'Fair', 'Low-water route used by large grazers.'),
  (10, 'Mopane Plains', 5, 'Healthy', 'Open plains with scattered shrubs and wildlife corridors.');

INSERT INTO staff (id, staff_code, staff_name, role, phone, assigned_area_id, availability_status) VALUES
  (1, 'STF-1001', 'Njeri Akinyi', 'Senior Ranger', '+2547001001', 1, 'Available'),
  (2, 'STF-1002', 'Daniel Okoth', 'Patrol Lead', '+2547001002', 1, 'On Patrol'),
  (3, 'STF-1003', 'Aisha Mutesa', 'Wildlife Officer', '+2567001003', 2, 'Available'),
  (4, 'STF-1004', 'Kiptoo Ngeno', 'Field Technician', '+2547001004', 3, 'Available'),
  (5, 'STF-1005', 'Mpho Tlhapi', 'Conservation Analyst', '+2677001005', 4, 'On Duty'),
  (6, 'STF-1006', 'Leah Wanjiku', 'Veterinary Support', '+2547001006', 3, 'Available'),
  (7, 'STF-1007', 'Samuel Kato', 'Ecologist', '+2567001007', 2, 'Available'),
  (8, 'STF-1008', 'Tariq Hassan', 'Security Ranger', '+2547001008', 5, 'On Patrol'),
  (9, 'STF-1009', 'Lina Mokoena', 'Monitoring Officer', '+2677001009', 4, 'Available'),
  (10, 'STF-1010', 'Benson Otieno', 'Operations Coordinator', '+2547001010', 5, 'Available');

INSERT INTO species (id, species_name, conservation_status, description) VALUES
  (1, 'African Lion', 'Vulnerable', 'Large predator with strong territorial behavior and social pride structure.'),
  (2, 'African Elephant', 'Critically Endangered', 'Keystone herbivore shaping landscape and migration routes.'),
  (3, 'Black Rhino', 'Critically Endangered', 'Threatened megaherbivore with strong poaching risk.'),
  (4, 'White Rhino', 'Near Threatened', 'Grassland grazer commonly monitored in open plains.'),
  (5, 'African Buffalo', 'Near Threatened', 'Herd-driven grazer common in savannah ecosystems.'),
  (6, 'Giraffe', 'Vulnerable', 'Tall browsing herbivore with seasonal movement patterns.'),
  (7, 'Zebra', 'Near Threatened', 'Highly visible plains grazer and important prey species.'),
  (8, 'Warthog', 'Least Concern', 'Ground-foraging species often seen in open grasslands.'),
  (9, 'Gazelle', 'Least Concern', 'Fast-moving antelope adapted to dry plains and margins.'),
  (10, 'Leopard', 'Vulnerable', 'Solitary predator in wooded and rugged habitats.'),
  (11, 'Cheetah', 'Vulnerable', 'Fastest land animal, highly sensitive to habitat fragmentation.'),
  (12, 'Hyena', 'Least Concern', 'Opportunistic predator and scavenger with large social groups.'),
  (13, 'Wildebeest', 'Least Concern', 'Migratory grazer central to ecosystem productivity.'),
  (14, 'Impala', 'Least Concern', 'Rapidly reproducing antelope widely distributed across protected areas.'),
  (15, 'Bushbaby', 'Least Concern', 'Nocturnal primate common in scrub and woodland edges.'),
  (16, 'Mountain Gorilla', 'Endangered', 'Highly endangered primate sensitive to habitat disturbance.'),
  (17, 'Chimpanzee', 'Endangered', 'Intelligent primate dependent on forest canopy connectivity.'),
  (18, 'Red River Hog', 'Least Concern', 'Forest-floor omnivore with strong group dynamics.'),
  (19, 'African Forest Elephant', 'Critically Endangered', 'Smaller-bodied elephant critical to seed dispersal in rainforest.'),
  (20, 'Okapi', 'Endangered', 'Rare nocturnal forest ungulate native to Congo basin ecosystems.'),
  (21, 'African Wild Dog', 'Endangered', 'Highly social predator vulnerable to fragmentation and disease.'),
  (22, 'Cape Buffalo', 'Near Threatened', 'Strong herd species central to grassland food webs.'),
  (23, 'Bongo', 'Endangered', 'Shy antelope of dense montane and forest habitats.'),
  (24, 'Waterbuck', 'Least Concern', 'Large antelope often found grazing near permanent water.'),
  (25, 'Kudu', 'Least Concern', 'Bushland antelope adapted to dry scrub environments.'),
  (26, 'Eland', 'Least Concern', 'Largest antelope known for herd migrations and adaptability.'),
  (27, 'Baboon', 'Least Concern', 'Highly adaptable primate relevant to human-wildlife conflict.'),
  (28, 'Aardvark', 'Least Concern', 'Nocturnal burrowing mammal important to soil health.'),
  (29, 'African Pangolin', 'Critically Endangered', 'Heavily trafficked insectivore at severe risk of extinction.'),
  (30, 'Hippopotamus', 'Vulnerable', 'Large semi-aquatic herbivore sensitive to riverbank disturbance.'),
  (31, 'Crocodile', 'Least Concern', 'Top aquatic predator in riverine and marsh habitats.'),
  (32, 'African Fish Eagle', 'Least Concern', 'Apex bird of prey commonly seen over lakes and wetlands.'),
  (33, 'Grey Crowned Crane', 'Endangered', 'Wetland bird highly sensitive to habitat transformation.'),
  (34, 'Secretary Bird', 'Near Threatened', 'Ground foraging raptor important in grassland pest control.'),
  (35, 'Marabou Stork', 'Least Concern', 'Scavenging bird common around aquatic feeding zones.'),
  (36, 'Pelican', 'Least Concern', 'Large waterbird tracking seasonal flood events.'),
  (37, 'African Spoonbill', 'Least Concern', 'Wading bird reliant on wetland productivity.'),
  (38, 'Sitatunga', 'Least Concern', 'Swamp antelope adapted to marshland vegetation.'),
  (39, 'Sable Antelope', 'Least Concern', 'Open-country antelope strongly tied to grass plains.'),
  (40, 'Roan Antelope', 'Least Concern', 'Open woodland antelope valued in transboundary drives.'),
  (41, 'Pangolin', 'Critically Endangered', 'Highly trafficked species with limited reproductive output.'),
  (42, 'African Civet', 'Least Concern', 'Nocturnal carnivore occupying woodland and scrub habitats.'),
  (43, 'Serval', 'Least Concern', 'Medium-sized cat adapted to tall grass and wetland margins.'),
  (44, 'Genet', 'Least Concern', 'Small carnivore occupying dense cover and feeding corridors.'),
  (45, 'Meerkat', 'Least Concern', 'Social mongoose common in arid grassland and rocky habitats.'),
  (46, 'Aardwolf', 'Least Concern', 'Termite-eating nocturnal carnivore of dry grasslands.'),
  (47, 'Red Duiker', 'Near Threatened', 'Small forest antelope sensitive to illegal bushmeat trade.'),
  (48, 'Blue Wildebeest', 'Least Concern', 'Common migratory grazer important for predator food webs.'),
  (49, 'Black-backed Jackal', 'Least Concern', 'Adaptable predator of open areas and edges.'),
  (50, 'African Hare', 'Least Concern', 'Fast-moving grazer important in grassland food chains.');

INSERT INTO animals (id, animal_code, species_id, age, health_status, location, protected_area_id) VALUES
  (1, 'AN-0001', 1, 5, 'Healthy', 'Central Grassland', 1),
  (2, 'AN-0002', 2, 12, 'Stable', 'Riverline Path', 1),
  (3, 'AN-0003', 3, 7, 'Recovering', 'North Fence', 1),
  (4, 'AN-0004', 4, 9, 'Healthy', 'Acacia Belt', 1),
  (5, 'AN-0005', 5, 4, 'Healthy', 'Central Plains', 1),
  (6, 'AN-0006', 6, 6, 'Stable', 'Water Crossing', 1),
  (7, 'AN-0007', 7, 3, 'Healthy', 'Savannah Edge', 1),
  (8, 'AN-0008', 8, 2, 'Healthy', 'Hillside Track', 1),
  (9, 'AN-0009', 9, 1, 'Healthy', 'Open Grazing', 1),
  (10, 'AN-0010', 10, 8, 'Stable', 'Rocky Cover', 1),
  (11, 'AN-0011', 11, 4, 'Healthy', 'South Run', 2),
  (12, 'AN-0012', 12, 5, 'Stable', 'Forest Edge', 2),
  (13, 'AN-0013', 13, 2, 'Healthy', 'River Meadow', 2),
  (14, 'AN-0014', 14, 3, 'Healthy', 'Rainforest Fringe', 2),
  (15, 'AN-0015', 15, 1, 'Healthy', 'Canopy Base', 2),
  (16, 'AN-0016', 16, 11, 'Stable', 'Upper Ridge', 2),
  (17, 'AN-0017', 17, 9, 'Recovering', 'Forest Loop', 2),
  (18, 'AN-0018', 18, 4, 'Healthy', 'Swamp Margin', 2),
  (19, 'AN-0019', 19, 10, 'Stable', 'Deep Forest', 2),
  (20, 'AN-0020', 20, 6, 'Healthy', 'Creek Path', 2),
  (21, 'AN-0021', 21, 5, 'Healthy', 'Athi Basin', 3),
  (22, 'AN-0022', 22, 7, 'Stable', 'Open Woodland', 3),
  (23, 'AN-0023', 23, 8, 'Stable', 'South Slope', 3),
  (24, 'AN-0024', 24, 4, 'Healthy', 'Waterhole', 3),
  (25, 'AN-0025', 25, 6, 'Healthy', 'Dry Creek', 3),
  (26, 'AN-0026', 26, 9, 'Healthy', 'Northern Game Trail', 3),
  (27, 'AN-0027', 27, 7, 'Stable', 'Rocky Outcrop', 3),
  (28, 'AN-0028', 28, 3, 'Healthy', 'Burrow Cluster', 3),
  (29, 'AN-0029', 29, 2, 'Critical', 'Dense Brush', 3),
  (30, 'AN-0030', 30, 11, 'Stable', 'River Bend', 3),
  (31, 'AN-0031', 31, 8, 'Healthy', 'Papyrus Marsh', 4),
  (32, 'AN-0032', 32, 5, 'Healthy', 'Lagoon Edge', 4),
  (33, 'AN-0033', 33, 3, 'Stable', 'Wetland Center', 4),
  (34, 'AN-0034', 34, 4, 'Healthy', 'Open Marsh', 4),
  (35, 'AN-0035', 35, 2, 'Healthy', 'Feeding Ground', 4),
  (36, 'AN-0036', 36, 6, 'Stable', 'Island Shelf', 4),
  (37, 'AN-0037', 37, 2, 'Healthy', 'Reed Bank', 4),
  (38, 'AN-0038', 38, 4, 'Healthy', 'Swamp Trail', 4),
  (39, 'AN-0039', 39, 5, 'Stable', 'Dry Grass Edge', 4),
  (40, 'AN-0040', 40, 7, 'Healthy', 'Mopane Plain', 4),
  (41, 'AN-0041', 41, 3, 'Recovering', 'Crocodile Ridge', 5),
  (42, 'AN-0042', 42, 5, 'Healthy', 'Thorn Patch', 5),
  (43, 'AN-0043', 43, 4, 'Stable', 'Tall Grass', 5),
  (44, 'AN-0044', 44, 2, 'Healthy', 'Tree Line', 5),
  (45, 'AN-0045', 45, 2, 'Healthy', 'Rocky Ridge', 5),
  (46, 'AN-0046', 46, 3, 'Stable', 'Dry Valley', 5),
  (47, 'AN-0047', 47, 4, 'Healthy', 'Forest Fringe', 5),
  (48, 'AN-0048', 48, 6, 'Healthy', 'Open Plateau', 5),
  (49, 'AN-0049', 49, 5, 'Healthy', 'Gully Line', 5),
  (50, 'AN-0050', 50, 1, 'Healthy', 'Low Grassland', 5);

INSERT INTO conservation_programs (id, program_name, protected_area_id, description, status) VALUES
  (1, 'Savannah Recovery Initiative', 1, 'Habitat restoration and anti-poaching patrol support.', 'Active'),
  (2, 'Forest Corridor Protection', 2, 'Primate habitat monitoring and canopy restoration.', 'Active'),
  (3, 'Urban Wildlife Resilience', 3, 'Human-wildlife conflict mitigation and community engagement.', 'Active'),
  (4, 'Wetland Ecosystem Recovery', 4, 'Floodplain restoration and bird population monitoring.', 'Planning'),
  (5, 'Predator Corridor Security', 5, 'Predator monitoring and ranger optimization.', 'Active');

INSERT INTO resources (id, resource_name, resource_type, quantity, condition, protected_area_id, availability_status) VALUES
  (1, 'Patrol Vehicle Alpha', 'Vehicle', 1, 'Operational', 1, 'Available'),
  (2, 'Drone Unit 2', 'Drone', 2, 'Operational', 1, 'Available'),
  (3, 'Camera Trap Kit', 'Monitoring', 10, 'Good', 2, 'Available'),
  (4, 'Field Medical Pack', 'Medical', 5, 'Ready', 3, 'In Use'),
  (5, 'Water Tanker', 'Logistics', 1, 'Operational', 4, 'Available'),
  (6, 'Radio Set', 'Communications', 12, 'Good', 5, 'Available'),
  (7, 'Snare Detector Kit', 'Security', 4, 'Ready', 1, 'Available'),
  (8, 'Tracking GPS Unit', 'Monitoring', 8, 'Operational', 2, 'In Use'),
  (9, 'Firebreak Equipment', 'Safety', 6, 'Good', 3, 'Available'),
  (10, 'Boat Access Kit', 'Transport', 2, 'Operational', 4, 'Available'),
  (11, 'Night Vision Scope', 'Security', 5, 'Ready', 5, 'Available'),
  (12, 'Rapid Response Pack', 'Medical', 3, 'Ready', 5, 'In Use');

INSERT INTO patrols (id, patrol_code, staff_id, protected_area_id, patrol_date, status) VALUES
  (1, 'PAT-001', 2, 1, '2026-01-08', 'Completed'),
  (2, 'PAT-002', 8, 5, '2026-01-09', 'Completed'),
  (3, 'PAT-003', 1, 1, '2026-01-12', 'Completed'),
  (4, 'PAT-004', 3, 2, '2026-01-14', 'In Progress'),
  (5, 'PAT-005', 4, 3, '2026-01-16', 'Completed'),
  (6, 'PAT-006', 6, 3, '2026-01-17', 'Completed'),
  (7, 'PAT-007', 7, 2, '2026-01-19', 'Scheduled'),
  (8, 'PAT-008', 10, 5, '2026-01-21', 'Completed'),
  (9, 'PAT-009', 2, 1, '2026-01-22', 'Completed'),
  (10, 'PAT-010', 5, 4, '2026-01-25', 'In Progress'),
  (11, 'PAT-011', 9, 4, '2026-01-27', 'Completed'),
  (12, 'PAT-012', 8, 5, '2026-01-30', 'Scheduled'),
  (13, 'PAT-013', 1, 1, '2026-02-02', 'Completed'),
  (14, 'PAT-014', 3, 2, '2026-02-04', 'Completed'),
  (15, 'PAT-015', 10, 5, '2026-02-06', 'Completed');

INSERT INTO incidents (id, incident_code, incident_type, description, severity, protected_area_id, location, reported_date, status) VALUES
  (1, 'INC-001', 'Poaching Alert', 'Ranger team reported suspicious vehicle activity near the northern fence.', 'High', 1, 'Northern Fence', '2026-01-05', 'Open'),
  (2, 'INC-002', 'Animal Injury', 'Elephant calf injured after a wire snare was discovered along a trail.', 'Critical', 1, 'Riverline Path', '2026-01-08', 'Resolved'),
  (3, 'INC-003', 'Illegal Logging', 'Tree cutting near the forest corridor threatens primate movement.', 'High', 2, 'Forest Edge', '2026-01-10', 'Open'),
  (4, 'INC-004', 'Human-Wildlife Conflict', 'Cattle herders entered the buffer zone near the Athi Basin.', 'Medium', 3, 'Athi Basin', '2026-01-12', 'Monitoring'),
  (5, 'INC-005', 'Flooding', 'Seasonal flooding submerged part of the marsh trail and limited access.', 'Medium', 4, 'Wetland Center', '2026-01-15', 'Resolved'),
  (6, 'INC-006', 'Predator Movement', 'Large lion pride spotted near patrol access road at night.', 'High', 5, 'Crocodile Ridge', '2026-01-18', 'Open'),
  (7, 'INC-007', 'Fire Risk', 'Dry shrubs ignited near the acacia belt after a lightning strike.', 'High', 1, 'Acacia Belt', '2026-01-19', 'Open'),
  (8, 'INC-008', 'Wildlife Trap', 'Snare recovered in dense cover near the forest river crossing.', 'High', 2, 'River Basin', '2026-01-20', 'Monitoring'),
  (9, 'INC-009', 'Vehicle Collision', 'Antelope collision reported near the main loop road.', 'Medium', 3, 'Main Loop', '2026-01-21', 'Resolved'),
  (10, 'INC-010', 'Habitat Degradation', 'Encroachment near the wetland edge is reducing bird nesting habitat.', 'Medium', 4, 'Papyrus Marsh', '2026-01-22', 'Open'),
  (11, 'INC-011', 'Poacher Sighting', 'Two suspects observed with a weapon near the ridge path.', 'Critical', 5, 'Rocky Ridge', '2026-01-26', 'Open'),
  (12, 'INC-012', 'Disease Alert', 'Wild dog footprint cluster suggests possible disease stress in migration corridor.', 'High', 5, 'South Plateau', '2026-01-28', 'Monitoring'),
  (13, 'INC-013', 'Water Shortage', 'Dry creek was locally stressed, affecting grazing access and animal movement.', 'Medium', 3, 'Dry Creek', '2026-01-30', 'Resolved'),
  (14, 'INC-014', 'Illegal Fishing', 'Fishing nets found in marsh and along riverbanks.', 'Medium', 4, 'Lagoon Edge', '2026-02-02', 'Open'),
  (15, 'INC-015', 'Trail Blockage', 'Heavy storm debris blocked access to the central grassland patrol route.', 'Low', 1, 'Central Grassland', '2026-02-05', 'Resolved');

INSERT INTO rescues (id, animal_id, rescue_date, location, animal_condition, rehabilitation_status) VALUES
  (1, 29, '2026-01-06', 'Dense Brush', 'Critical injury from snare', 'Under Treatment'),
  (2, 3, '2026-01-09', 'North Fence', 'Wounded leg', 'Stable Recovery'),
  (3, 17, '2026-01-13', 'Forest Loop', 'Stress and dehydration', 'Monitoring'),
  (4, 13, '2026-01-18', 'River Meadow', 'Minor trauma', 'Released'),
  (5, 21, '2026-01-24', 'Athi Basin', 'Severe exhaustion', 'Rehabilitation'),
  (6, 33, '2026-01-31', 'Wetland Center', 'Wing injury', 'Under Treatment'),
  (7, 41, '2026-02-03', 'Crocodile Ridge', 'Minor wound', 'Stable Recovery'),
  (8, 2, '2026-02-04', 'Riverline Path', 'Lame front leg', 'Monitoring'),
  (9, 45, '2026-02-06', 'Rocky Ridge', 'Skin abrasion', 'Released'),
  (10, 16, '2026-02-08', 'Upper Ridge', 'Torn tissue', 'Under Treatment');
